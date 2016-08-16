package com.stanslab.jimdb.select;

import com.stanslab.jimdb.select.operation.AndOperation;
import com.stanslab.jimdb.select.operation.EqualOperation;
import com.stanslab.jimdb.select.operation.OrOperation;

public class OperationFactory {
	
	private OperationParam operationParam;
	
	public OperationFactory(OperationParam operationParam) {
		this.operationParam = operationParam;
	}
	
	public Operation getOperation(Op operator) {
		Operation operation = null;
		switch (operator) {
		case EQ: {
			operation = new EqualOperation(operationParam);
			break;
		}
		case NEQ: {
			break;
		}
		case LT: {
			break;
		}
		case LTE: {
			break;
		}
		case GT: {
			break;
		}
		case GTE: {
			break;
		}
		case CONTAINS: {
			break;
		}
		case EMPTY: {
			break;
		}
		case END_WITH: {
			break;
		}
		case START_WITH: {
			break;
		}
		}
		return operation;
	}

	public Operation getOperation(LogOp operator) {
		Operation operation = null;
		switch (operator) {
		case AND: {
			operation = new AndOperation(operationParam);
			break;
		}
		case OR: {
			operation = new OrOperation(operationParam);
			break;
		}
		case NOT: {
			break;
		}
		}
		return operation;
	}
	
}
