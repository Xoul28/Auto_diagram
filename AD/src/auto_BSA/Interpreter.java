package auto_BSA;

import java.util.HashSet;
import java.util.Set;

public class Interpreter {
	private static Set<String> keywords;
	private String code;
	private int pos;
	private int len;
	
	static {
		keywords = new HashSet<String>();
		keywords.add("for");
		keywords.add("switch");
		keywords.add("if");
		keywords.add("while");
		keywords.add("do");
	}
	
	private static boolean isKeyword(String str) {
		return keywords.contains(str);
	}
	
	public Interpreter(String code) {
		this.code = code;
		this.pos = 0;
		this.len = code.length();
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
	
	private boolean peekElse() {
		skipWhitespaces();
		StringBuilder keyword = new StringBuilder();
		int i = pos;
		char current = getCurrentChar();
		while (!isEnd() && i - pos < 4) {
			keyword.append(current);
			String blockType = keyword.toString();
			// if it is keyword with no-letter char next to it
			if (blockType.equals("else") && !Character.isLetter(code.charAt(i + 1))) {
				return true;
			}
			i++;
			current = code.charAt(i);
		}
		return false;
	}
	
	private void skipElse() {
		if (peekElse()) {
			for (int i = 0; i < 4; i++) {
				nextChar();
			}
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
	
	public void analyze() {
		// StringBuilder buffer = new StringBuilder();
		while (!isEnd()) {
			System.out.print(readStatement());
			/*
			char current = getCurrentChar();
			buffer.append(current);
			String str = buffer.toString();
			// if ('buffer' is a c++ keyword)
			if (isKeyword(str)) {
				nextChar();
				System.out.println(str);
				if (str.equals("do")) {
					System.out.println('{' + readStatement() + '}');
					System.out.println('(' + readCondition() + ')');
				} else {
					System.out.println('(' + readCondition() + ')');
					System.out.println('{' + readStatement() + '}');
				}
			}
			if (Character.isWhitespace(current)) {
				buffer.setLength(0);
			}
			nextChar();//*/
		}
	}
	
	private String readBlock(String blockType) {
		StringBuilder block = new StringBuilder();
		block.append("$" + blockType + "$ ");
		if (blockType.equals("do")) {
			block.append("{\n" + readStatement() + "} ");
			block.append('(' + readCondition() + ")\n");
		} else {
			block.append('(' + readCondition() + ") ");
			block.append("{\n" + readStatement() + "}");
			if (blockType.equals("if") && peekElse()) {
				skipElse();
				block.append(" $else$ ");
				block.append("{\n" + readStatement() + "}\n");
			} else {
				block.append('\n');
			}
		}
		return block.toString();
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
	
	private String readStatement() {
		// skip space chars
		skipWhitespaces();
		
		if (isEnd()) return "";
		
		char current = getCurrentChar();
		
		// if it is only 1 'expression;' or keyword
		if (current != '{') {
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
			return statement.toString().trim() + ";\n";
		}
		
		// if it is {expression1; ...}
		return readCompoundStatement();
		/*
		statement.setLength(0);
		
		int braces = 1;
		while (braces != 0) {
			current = getCurrentChar();
			// TODO обработка вложенных блоков
			/*
//			while (Character.isWhitespace(current)) {
//				nextChar();
//				current = getCurrentChar();
//			}
			switch (current) {
			case '}':
				braces--;
				break;
			case '{':
				braces++;
				break;
			}
			statement.append(current);
			nextChar();
		}
		statement.deleteCharAt(statement.length() - 1); // delete last '}'
		return statement.toString().trim();*/
	}
	
	private String readCompoundStatement() {
		nextChar(); // skip first '{'
		skipWhitespaces();
		StringBuilder statement = new StringBuilder();
		while (getCurrentChar() != '}') {
			statement.append(readStatement());
			skipWhitespaces();
		}
		nextChar();
		return statement.toString();
	}
	
}
