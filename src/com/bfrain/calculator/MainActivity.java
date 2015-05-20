package com.bfrain.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvAnswer;
	TextView tvFullExpression;
	float answer;
	boolean isInitial = true;
	static final String DEFAULT_EXPRESSION = "0";
	String currentExp = DEFAULT_EXPRESSION;

	String lastOperator = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvAnswer = (TextView)findViewById(R.id.tvAnswer);
		tvFullExpression = (TextView)findViewById(R.id.tvInputExpression);
	}
	
	public void initCurrentExp(String initialExpression) {
		isInitial = true;
		currentExp = initialExpression;
	}
	
	public void setCurrentExp(String exp) {
		if (isInitial) {
			tvFullExpression.setText("");
		}
		if (exp.length() == 0) {
			initCurrentExp(DEFAULT_EXPRESSION);
		} else {
			currentExp = exp;
			isInitial = false;
		}
		tvAnswer.setText(currentExp);
	}
		
	private boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	private boolean isOperatorOrBracket(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
	}
	
	public void onBackspaceButtonClicked(View v) {	
    	setCurrentExp(currentExp.substring(0, currentExp.length() - 1));    
	}
	
	public void onButtonClicked(View v) {	
		// define the button that invoked the listener by id
		String inputText = ((TextView)v).getText().toString();
		switch (v.getId()) {
        // "=" tapped -- time to calculate and present result
        case R.id.btnEqual:
        	tvFullExpression.setText(currentExp);
        	Expression exp = new Expression(currentExp.toString()); 
        	String formattedResultString = exp.getFormattedResultString();
        	tvAnswer.setText(formattedResultString);
        	if (formattedResultString.equals(getResources().getString(R.string.error_message))) {
        		initCurrentExp(DEFAULT_EXPRESSION);
        	} else {
        		initCurrentExp(formattedResultString);
        	}
        	break;
        // operator -- If last char of currentExp is an operator, replace it to new operator. Append the operator directly otherwise.
        case R.id.btnPlus:
        case R.id.btnMinus:
        case R.id.btnMultiplication:
        case R.id.btnDivision:
        	if (isOperator(currentExp.charAt(currentExp.length() - 1))) {
        		setCurrentExp(currentExp.substring(0, currentExp.length() - 1) + inputText);
        	} else {
        		setCurrentExp(currentExp + inputText);
        	}
        	break;
        // dot
        case R.id.btnDot:
        	if (currentExp.charAt(currentExp.length() - 1) != '.') {
        		String stringToAppend = isOperatorOrBracket(currentExp.charAt(currentExp.length() -1)) ? "0." : ".";
        		setCurrentExp(currentExp + stringToAppend);
        	}
        	break;
        // digit or bracket
        default:
        	if (isInitial) {
        		setCurrentExp(inputText);
        	} else {
        		setCurrentExp(currentExp + inputText);
        	}
        }      
      }
}
