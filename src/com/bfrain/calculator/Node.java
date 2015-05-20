package com.bfrain.calculator;

public class Node {
	Node left;
	Node right;
	String key;	

	Node(Node left, Node right, String key) {
		this.left = left;
		this.right = right;
		this.key = key;
	}
	
	float getValue() throws ArithmeticException {
		float leftValue = (left != null) ? left.getValue() : -1;
		float rightValue = (right != null) ? right.getValue() : -1;
		switch (key.charAt(0)) {
		case '+':
			return leftValue + rightValue;
		case '-':
			return leftValue - rightValue;
		case '*':
			return leftValue * rightValue;
		case '/':
			if (rightValue == 0) {
				throw new ArithmeticException();
			}
			return leftValue / rightValue;
		case '%':
			if (rightValue == 0) {
				throw new ArithmeticException();
			}
			return leftValue % rightValue;
		default:
			return Float.parseFloat(key);	
		}
	}
}
