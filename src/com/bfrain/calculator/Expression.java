package com.bfrain.calculator;

public class Expression {
	static final String ERROR_MESSAGE = "³ö´íÀ²";
	String expressionString;
	Node rootNode;
	TokenScanner tokenScanner;
	
	Expression(String exp) {
		this.expressionString = exp;
		tokenScanner = new TokenScanner(exp);
		rootNode = getExpression();
	}
		
	Node getPrimary() {
		String token = tokenScanner.nextToken();
		Node newNode;
		if (! token.equals("(")) {
			newNode = new Node(null, null, token);
		} else {
			newNode = getExpression();
			tokenScanner.nextToken(); // read ')'
		}
		return newNode;
	}

	Node getTerm() {
		Node tmp = getPrimary();
		char ch = tokenScanner.getChar();
		while (true) {
			if (ch == '*' || ch == '/' || ch == '%') {
				Node newNode = new Node(tmp, getPrimary(), ch+"");
				tmp = newNode;
				ch = tokenScanner.getChar();
			} else {
				tokenScanner.ungetChar();
				return tmp;
			}
		}
	}
		
	Node getExpression() {
		Node tmp = getTerm();
		char ch = tokenScanner.getChar();
		while (true) {
			if (ch == '+' || ch == '-') {
				Node newNode = new Node(tmp, getTerm(), ch + "");
				tmp = newNode;
				ch = tokenScanner.getChar();
			} else {
				tokenScanner.ungetChar();
				return tmp;
			}
		}
	}

	private boolean isInteger(float num) {
		return (num > Integer.MIN_VALUE) && (num < Integer.MAX_VALUE) && (num == Math.ceil(num));
	}
	
	float getCalculatedResult() {
		return rootNode.getValue();
	}
	
	String getFormattedResultString() {
		try {
			float result = rootNode.getValue();
	       	if (isInteger(result)) {
        		return Integer.toString((int)result);
        	} else {
        		return Float.toString(result);
        	}
		} catch (ArithmeticException e) {
			return ERROR_MESSAGE;
		}
	}
}
