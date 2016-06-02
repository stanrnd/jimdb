package com.jimdb.select;

import com.jimdb.select.Op;

public class Filter {
	
	private Op operator;
	
	private String column;
	
	private Object value;
	
	private Filter leftOperand;
	
	private Filter rightOperand;
	
	public Filter(String column, Op operator, Object value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
		
	}
	
	public Filter(Filter leftOperand, Op operator, Filter rightOperand) {
		this.leftOperand = leftOperand;
		this.operator = operator;
		this.rightOperand = rightOperand;
	}

	public Op getOperator() {
		return operator;
	}

	public String getColumn() {
		return column;
	}

	public Object getValue() {
		return value;
	}

	public Filter getLeftOperand() {
		return leftOperand;
	}

	public Filter getRightOperand() {
		return rightOperand;
	}
	
}
