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
	
	private boolean isEnd() {
		return pos >= len - 1;
	}
	
	public void analyze() {
		// StringBuilder buffer = new StringBuilder();
		while (!isEnd()) {
			System.out.println(readStatement());
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
		block.append(blockType + '\n');
		if (blockType.equals("do")) {
			block.append("{\n" + readStatement() + "\n}\n");
			block.append('(' + readCondition() + ")\n");
		} else {
			block.append('(' + readCondition() + ")\n");
			block.append("{\n" + readStatement() + "\n}\n");
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
		while (Character.isWhitespace(getCurrentChar()) && !isEnd()) {
			nextChar();
		}
		
		if (isEnd()) return "";
		
		char current = getCurrentChar();
		StringBuilder statement = new StringBuilder();
		
		// if it is only 1 'expression;' or keyword
		if (current != '{') {
			// try to read keyword-block
			while (true) {
				current = getCurrentChar();
				if (Character.isWhitespace(current) || current == ';') {
					break;
				}
				statement.append(current);
				String blockType = statement.toString();
				if (isKeyword(blockType)) {
					nextChar();
					return readBlock(blockType).trim();
				}
				nextChar();
			}
			// it is 'expression;'
			while (current != ';') {
				nextChar();
				statement.append(current);
				current = getCurrentChar();
			}
			nextChar();
			return statement.toString().trim() + ';';
		}
		
		// if it is {expression1; ...}
		statement.setLength(0);
		nextChar(); // skip first '{'
		int braces = 1;
		while (braces != 0) {
			current = getCurrentChar();
			/*
			while (Character.isWhitespace(current)) {
				nextChar();
				current = getCurrentChar();
			}//*/
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
		return statement.toString().trim();
	}
	
}
