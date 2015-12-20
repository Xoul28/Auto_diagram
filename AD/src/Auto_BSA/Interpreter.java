package Auto_BSA;

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
	
	public void analyze() {
		StringBuilder buffer = new StringBuilder();
		while (pos < len) {
			char current = getCurrentChar();
			buffer.append(current);
			String str = buffer.toString();
			// if ('buffer' is a c++ keyword)
			if (keywords.contains(str)) {
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
			nextChar();
		}
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
		while (Character.isWhitespace(getCurrentChar())) {
			nextChar();
		}
		
		char current = getCurrentChar();
		StringBuilder statement = new StringBuilder();
		
		// if it is only 1 'expression;'
		if (current != '{') {
			while (current != ';') {
				statement.append(current);
				nextChar();
				current = getCurrentChar();
			}
			return statement.toString().trim();
		}
		
		// if it is {expression1; ...}
		statement.setLength(0);
		nextChar();
		int braces = 1;
		while (braces != 0) {
			current = getCurrentChar();
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
		statement.deleteCharAt(statement.length() - 1);
		return statement.toString().trim();
	}
	
}
