package com.jimdb.select;

import com.jimdb.select.Op;

public class Filter {
	
	private Op operator;
	
	private String field;
	
	private Object value;
	
	private LogOp logicalOperator;
	
	private Filter leftOperand;
	
	private Filter rightOperand;
	
	public Filter(String field, Op operator, Object value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
		
	}
	
	public Filter(Filter leftOperand, LogOp logicalOperator, Filter rightOperand) {
		this.leftOperand = leftOperand;
		this.logicalOperator = logicalOperator;
		this.rightOperand = rightOperand;
	}

	public Op getOperator() {
		return operator;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public LogOp getLogicalOperator() {
		return logicalOperator;
	}

	public Filter getLeftOperand() {
		return leftOperand;
	}

	public Filter getRightOperand() {
		return rightOperand;
	}
	
}
