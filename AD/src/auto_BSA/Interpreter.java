package auto_BSA;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
	private static Set<String> keywords;
	private static Set<Pattern> types;
	private static Set<Pattern> ioOperations;
	private static Set<Pattern> ioFunctions;
	private static Set<Pattern> typeConversions;
	private static Set<Pattern> typeInParentheses;
	private String code;
	private int pos;
	private int len;
	private boolean flowchartMode;
	private Set<String> functions;
	
	static {
		keywords = new HashSet<String>();
		keywords.add("for");
		keywords.add("switch");
		keywords.add("if");
		keywords.add("while");
		keywords.add("do");
		keywords.add("case");
		
		types = new HashSet<Pattern>();
		types.add(Pattern.compile("(int[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(float[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(double[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(char[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(bool[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(short[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(long[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(signed[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(unsigned[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(const[\\s\\*]*)\\W"));
		types.add(Pattern.compile("(void[\\s\\*]*)\\W"));
		
		ioOperations = new HashSet<Pattern>();
		ioOperations.add(Pattern.compile("->\\s*Caption"));
		ioOperations.add(Pattern.compile("->\\s*Text"));
		ioOperations.add(Pattern.compile("->\\s*Value"));
		ioOperations.add(Pattern.compile("->\\s*Cells"));
		ioOperations.add(Pattern.compile("->\\s*Checked"));
		
		ioFunctions = new HashSet<Pattern>();
		ioFunctions.add(Pattern.compile("ShowMessage\\s*\\(([^;]*)\\)"));
		
		typeConversions = new HashSet<Pattern>();
		typeConversions.add(Pattern.compile("IntToStr\\s*\\(([^;]*)\\)"));
		typeConversions.add(Pattern.compile("StrToInt\\s*\\(([^;]*)\\)"));
		typeConversions.add(Pattern.compile("FloatToStr\\s*\\(([^;]*)\\)"));
		typeConversions.add(Pattern.compile("StrToFloat\\s*\\(([^;]*)\\)"));
		
		typeInParentheses = new HashSet<Pattern>();
		typeInParentheses.add(Pattern.compile("\\(\\s*int[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*float[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*double[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*char[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*bool[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*short[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*long[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*signed[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*unsigned[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*const[\\s\\*]*\\)"));
		typeInParentheses.add(Pattern.compile("\\(\\s*void[\\s\\*]*\\)"));
		
	}
	
	private static boolean isKeyword(String str) {
		return keywords.contains(str);
	}
	
	// code is a program code,
	// flowchartMode (default to true) enables I/O Blocks
	public Interpreter(String code, boolean flowchartMode) {
		this.code = code;
		this.pos = 0;
		this.len = code.length();
		this.flowchartMode = flowchartMode;
		this.functions = new HashSet<String>();
	}
	
	public Interpreter(String code) {
		this(code, true);
	}
	
	private char getCurrentChar() {
		return code.charAt(pos);
	}
	
	private void nextChar() {
		pos++;
	}
	
	private char peekNextChar() {
		return code.charAt(pos + 1);
	}
	
	private boolean peekWord(String word) {
		skipWhitespaces();
		StringBuilder keyword = new StringBuilder();
		int len = word.length();
		int i = pos;
		char current = getCurrentChar();
		while (!isEnd() && i - pos < len) {
			keyword.append(current);
			String blockType = keyword.toString();
			// if it is keyword with no-letter char next to it
			if (blockType.equals(word) && !Character.isLetter(code.charAt(i + 1))) {
				return true;
			}
			i++;
			current = code.charAt(i);
		}
		return false;
	}
	
	private boolean peekElse() {
		return peekWord("else");
	}
	
	private boolean peekCase() {
		return peekWord("case") || peekWord("default");
	}
	
	private void skipElse() {
		if (peekElse()) {
			for (int i = 0; i < 4; i++) {
				nextChar();
			}
		}
	}
	
	private void skipCase() {
		if (peekWord("case")) {
			for (int i = 0; i < 4; i++) {
				nextChar();
			}
		} else if (peekWord("default")) {
			for (int i = 0; i < 7; i++) {
				nextChar();
			}
		}
	}
	
	private void skipSemicolon() {
		skipWhitespaces();
		if (getCurrentChar() == ';') {
			nextChar();
		}
	}
	
	private boolean isEnd() {
		return pos >= len - 1;
	}
	
	private void skipWhitespaces() {
		while (Character.isWhitespace(getCurrentChar()) && !isEnd()) {
			nextChar();
		}
	}
	
	private String toFlowchartString(String statement) {
		if (!flowchartMode) {
			return statement + ";";
		}
		for (Pattern type : typeInParentheses) {
			statement = statement.replaceAll(type.pattern(), "").trim();
		}
		for (Pattern type : types) {
			Matcher matcher = type.matcher(statement);
			while (matcher.find()) {
				statement = statement.substring(0, matcher.start(1)) +
						  	statement.substring(matcher.end(1));
				matcher = type.matcher(statement);
			}
		}
		for (Pattern conversion : typeConversions) {
			Matcher matcher = conversion.matcher(statement);
			if (matcher.find()) {
				statement = matcher.group(1);
			}
		}
		statement = statement.trim();
		if (statement.endsWith(";")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		statement = statement.replaceAll("\\s\\s+", " ");
		return statement.trim();
	}
	
	private  boolean isIOBlock(String statement) {
		if (!flowchartMode) {
			return false;
		}
		for (Pattern iostr : ioOperations) {
			Matcher matcher = iostr.matcher(statement);
			if (matcher.find()) {
				return true;
			}
		}
		for (Pattern iostr : ioFunctions) {
			Matcher matcher = iostr.matcher(statement);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}
	
	private String toIOBlockString(String statement) {
		if (!flowchartMode) {
			return statement = ";";
		}
		for (Pattern iostr : ioFunctions) {
			Matcher matcher = iostr.matcher(statement);
			if (matcher.find()) {
				return toFlowchartString(matcher.group(1));
			}
		}
		String[] strs = statement.split("=");
		for (int i = 0; i < strs.length; i++) {
			if (!isIOBlock(strs[i])) {
				//strs[i] = strs[i].replaceAll("\\[\\s*\\w*\\s*\\]", "");
				return toFlowchartString(strs[i]);
			}
		}
		return statement.trim();
	}
	
	private boolean isFunctionCall(String statement) {
		Pattern funcName = Pattern.compile("\\W{0,1}(\\w+)\\s*\\(");
		Matcher matcher = funcName.matcher(statement);
		while (matcher.find()) {
			String func = matcher.group(1);
			if (functions.contains(func)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteComments() {
		Pattern directive = Pattern.compile("#.*$", Pattern.MULTILINE);
		Matcher matcher = directive.matcher(code);
		code = matcher.replaceAll("");

		Pattern multilineComment = Pattern.compile("[^/]/\\*.*?\\*/", Pattern.DOTALL);
		matcher = multilineComment.matcher(code);
		code = matcher.replaceAll("");
		
		Pattern onelineComment = Pattern.compile("//.*$", Pattern.MULTILINE);
		matcher = onelineComment.matcher(code);
		code = matcher.replaceAll("\n");
		
		len = code.length();
	}
	
	public List<Block> analyze(String name) {
		while (!isEnd()) {
			List<Block> b = readFunction(name);
			return b;
		}
		List<Block> b = new LinkedList<Block>();
		return b;
	}
	
	public List<Block> analyze() {
		return analyze("");
	}
	
	private List<Block> readBlock(String blockType) {
		List<Block> blocks = new LinkedList<Block>();
		if (blockType.equals("do")) {
			List<Block> body = new LinkedList<Block>();
			body.addAll(readStatement());
			String condition = readCondition();
			skipSemicolon();
			blocks.add(new DoWhileCycle(body.toArray(new Block[0]), condition));
		} else if (blockType.equals("switch")) {
//			String condition = readCondition();
//			List<Case> body = new LinkedList<Case>();
//			body.addAll(readCases());
//			blocks.add(new Switch(condition, body));
		} else if (blockType.equals("case")) {
//			Case block = readCase();
//			blocks.add(block);
		} else {
			String condition = readCondition();
			List<Block> body = new LinkedList<Block>();
			body.addAll(readStatement());
			if (blockType.equals("if") && peekElse()) {
				skipElse();
				List<Block> bodyElse = readStatement();
				blocks.add(new SelectorBlock(body.toArray(new Block[0]), condition, bodyElse.toArray(new Block[0])));
			} else if (blockType.equals("if")) {
				blocks.add(new SelectorBlock(body.toArray(new Block[0]), condition, new Block[0]));
			} else if (blockType.equals("for")) {
				blocks.add(new ForCycle(body.toArray(new Block[0]), condition));
			} else if (blockType.equals("while")) {
				blocks.add(new WhileCycle(body.toArray(new Block[0]), condition));
			} else {
				
			}
		}
		return blocks;
	}
	
	private String readCondition() {
		while (getCurrentChar() != '(') {
			nextChar();
		}
		nextChar();
		int parentheses = 1;
		StringBuilder condition = new StringBuilder();
		while (parentheses != 0) {
			char current = getCurrentChar();
			switch (current) {
			case ')':
				parentheses--;
				break;
			case '(':
				parentheses++;
				break;
			}
			condition.append(current);
			nextChar();
		}
		condition.deleteCharAt(condition.length() - 1);
		return condition.toString().trim();
	}
	
	private List<Block> readStatement() {
		// skip space chars
		skipWhitespaces();
		
		if (isEnd()) {
			List<Block> blocks = new LinkedList<Block>();
			blocks.add(new EmptyBlock());
			return blocks;
		}
		
		char current = getCurrentChar();
		
		// if it is only 1 'expression;' or keyword
		if (current != '{') {
			if (peekCase()) {
				List<Block> blocks = new LinkedList<Block>();
				blocks.add(new EmptyBlock());
				return blocks;
			}
			StringBuilder statement = new StringBuilder();
			// try to read keyword-block
			while (Character.isLetter(current)) {
				statement.append(current);
				String blockType = statement.toString();
				// if it is keyword with no-letter char next to it
				if (isKeyword(blockType) && !Character.isLetter(peekNextChar())) {
					nextChar(); // skip last keyword's letter
					return readBlock(blockType);
				}
				nextChar();
				current = getCurrentChar();
			}
			// it is 'expression;'
			while (current != ';') {
				statement.append(current);
				nextChar();
				current = getCurrentChar();
			}
			nextChar(); // skip ';'
			List<Block> blocks = new LinkedList<Block>();
			if (isIOBlock(statement.toString()) && flowchartMode) {
				blocks.add(new IOBlock(toIOBlockString(statement.toString())));
			} else if (isFunctionCall(statement.toString())) {
				blocks.add(new FunctionCall(statement.toString()));
			} else {
				String statementStr = toFlowchartString(statement.toString().trim());
				if (statementStr.equals("break")) {
					blocks.add(new BreakLine());
				} else if (statementStr.equals("continue")) {
					blocks.add(new ContinueLine());
				} else {
					blocks.add(new Statement(statementStr));
				}
			}
			return blocks;
		}
		
		// if it is {expression1; ...}
		nextChar(); // skip first '{'
		List<Block> blocks = readCompoundStatement();
		nextChar(); // skip '}'
		return blocks;
	}
	
	private List<Block> readCompoundStatement() {
		skipWhitespaces();
		List<Block> blocks = new LinkedList<Block>();
		while (getCurrentChar() != '}') {
			blocks.addAll(readStatement());
			if (blocks.get(blocks.size() - 1) instanceof EmptyBlock) {
				return blocks;
			}
			skipWhitespaces();
		}
		return blocks;
	}
	
	private Case readCase() {
		skipWhitespaces();
		// skip characters before 'case'
		while (!peekCase()) {
			nextChar();
		}
		StringBuilder value = new StringBuilder();
		
		skipCase();
		skipWhitespaces();
		char current = getCurrentChar();
		while (current != ':') {
			value.append(current);
			nextChar();
			skipWhitespaces();
			current = getCurrentChar();
		}
		nextChar(); // skip ':'
		// skipWhitespaces();
		List<Block> bodyCase = readCompoundStatement();
		return new Case(bodyCase.toArray(new Block[0]), value.toString());
	}
	
	private List<Case> readCases() {
		skipWhitespaces();
		nextChar(); // skip first '{'
		List<Case> blocks = new LinkedList<Case>();
		while (getCurrentChar() != '}') {
			blocks.add(readCase());
			skipWhitespaces();
		}
		nextChar(); // skip '}'
		return blocks;
	}
	
	private List<Block> readFunction(String name) {
		StringBuilder statement = new StringBuilder();
		Pattern funcName = Pattern.compile("\\W{0,1}(\\w+)\\s*\\(");
		while (!isEnd()) {
			char current = getCurrentChar();
			// it is a 'statement ... ;', not a function
			if (current == ';') {
				Matcher matcher = funcName.matcher(statement.toString().trim());
				if (matcher.find()) {
					String nameOfFunction = matcher.group(1);
					functions.add(nameOfFunction);
				}
				statement.setLength(0);
			} else if (current == '{') { // it is a function declaration
				nextChar(); // skip first '{'
				List<Block> blocks = readCompoundStatement();
				nextChar(); // skip '}'
				List<Block> function = new LinkedList<Block>();
				// check name of function
				Matcher matcher = funcName.matcher(statement.toString().trim());
				if (matcher.find()) {
					String nameOfFunction = matcher.group(1);
					functions.add(nameOfFunction);
					if (name.equals(nameOfFunction) || name.equals("")) {
						function.add(new Function(blocks.toArray(new Block[0]),
									 statement.toString().trim()));
						return function;
					}
				}
				statement.setLength(0);
			} else {
				statement.append(current);
			}
			nextChar();
		}
		return new LinkedList<Block>();
	}
	
	private List<Block> readFunction() {
		return readFunction("");
	}
	
}
