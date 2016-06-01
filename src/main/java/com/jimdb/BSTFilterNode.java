package com.jimdb;

import java.util.List;

public class BSTFilterNode {

	private Op operator;

	private String column;

	private Object value;

	private BSTFilterNode leftOperand;

	private BSTFilterNode rightOperand;
	
	private OperationParam operationParam;

	private List<Integer> result;
	
	public Op getOperator() {
		return operator;
	}

	public void setOperator(Op operator) {
		this.operator = operator;
	}

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

	public OperationParam getOperationParam() {
		return operationParam;
	}

	public void setOperationParam(OperationParam operationParam) {
		this.operationParam = operationParam;
	}

	public List<Integer> getResult() {
		return result;
	}

	public void setResult(List<Integer> result) {
		this.result = result;
	}
	
	public BSTFilterNode process() {
		if(leftOperand != null && rightOperand != null) {
			leftOperand.process();
			rightOperand.process();
		}
		Operation operation = OpertionFactory.getOperation(this.operator);
		this.result = operation.eval(this);
		return this;
	}
	
}
