package com.bfrain.calculator;

import android.util.Log;

public class TokenScanner {
	String str;
	int cursor = 0;
	
	TokenScanner(String str) {
		this.str = str;
	}
	
	boolean isOperatorOrBracket(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
	}
		
	String nextToken() {
		if (cursor >= str.length()) {
			return "";
		}
		if (!Character.isDigit(str.charAt(cursor))) {
			return str.charAt(cursor++) + "";
		} else { // is number
			String token = "";
			while (cursor < str.length() && !isOperatorOrBracket(str.charAt(cursor))) {
				token += str.charAt(cursor++);
			}
			return token;
		}
	}
	
	char getChar() {
		if (cursor >= str.length()) {
			return ' ';
		}
		return str.charAt(cursor++);
	}
	
	void ungetChar() {
		cursor--;
	}
}
