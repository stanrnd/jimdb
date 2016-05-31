package com.jimdb;

public class OpertionFactory {
	
	public static Operation getOperation(Op operator) {
		Operation operation = null;
		switch (operator) {
		case EQ: {
			operation = new EqualOperation();
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
		case AND: {
			break;
		}
		case OR: {
			break;
		}
		case NOT: {
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

}
