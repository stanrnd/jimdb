package com.jimdb.select;

import java.util.List;

import com.jimdb.select.Operation;

public class BSTFilterNode {

	private String column;

	private Object value;

	private BSTFilterNode leftOperand;

	private BSTFilterNode rightOperand;
	
	private Operation operation;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public BSTFilterNode getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(BSTFilterNode leftOperand) {
		this.leftOperand = leftOperand;
	}

	public BSTFilterNode getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(BSTFilterNode rightOperand) {
		this.rightOperand = rightOperand;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public List<Integer> process() {
		if(leftOperand != null && rightOperand != null) {
			List<Integer> leftList = leftOperand.process();
			List<Integer> rightList = rightOperand.process();
			return operation.eval(leftList, rightList);
		}
		return operation.eval(column, value);
	}
	
}
