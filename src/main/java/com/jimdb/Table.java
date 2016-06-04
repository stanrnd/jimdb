package com.jimdb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import com.jimdb.ds.JimdbTreeMap;
import com.jimdb.select.BSTFilterNode;
import com.jimdb.select.Filter;
import com.jimdb.select.Operation;
import com.jimdb.select.OperationFactory;
import com.jimdb.select.OperationParam;

public class Table<T> {
	
	private int lastRowId;
	
	private int maxNoOfRows;

	private IndexConfig indexConfig;
	
	private Object[] data;
	
	private Map<String, JimdbTreeMap<Object, List<Integer>>> dataMapping;
	
	private Stack<Integer> emptyIndexes;
	
	public Table(int maxNoOfRows, IndexConfig indexConfig) {
		this.data = new Object[maxNoOfRows];
		this.indexConfig = indexConfig;
		this.emptyIndexes = new Stack<Integer>();
		this.dataMapping = new HashMap<String, JimdbTreeMap<Object,List<Integer>>>();
		for(String col:indexConfig.getColIndexes()) {
			dataMapping.put(col, new JimdbTreeMap<Object, List<Integer>>());
		}
	}
	
	public void insert(T bean) {
		try {
			int emptyRowId = findEmptyRowId();
			data[emptyRowId] = bean;
			for(String col:indexConfig.getColIndexes()) {
				Field field = bean.getClass().getDeclaredField(col);
				field.setAccessible(true);
		        Object value = field.get(bean);
		        List<Integer> rows = dataMapping.get(col).get(value);
				if(rows == null) {
					dataMapping.get(col).put(value, new ArrayList<Integer>());
				}
				dataMapping.get(col).get(value).add(emptyRowId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(List<T> beans) {
		for(T t:beans) {
			insert(t);
		}
	}
	
	public List<T> select() {
		
		return null;
	}
	
	private int findEmptyRowId() {
		if(!emptyIndexes.isEmpty()) {
			return emptyIndexes.pop();
		} else if(maxNoOfRows == (lastRowId+1)) {
			System.out.println("Warn: Number of records reaches the max limit. Please increase the Max Rows for better performance.");
			// double the data array size
		}
		return ++lastRowId;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(Filter filter) {
		BSTFilterNode bstFilterNode = new BSTFilterNode();
		OperationFactory operationFactory = new OperationFactory(new OperationParam(lastRowId, maxNoOfRows, indexConfig, data, dataMapping, emptyIndexes));
		bstFilterNode(filter, bstFilterNode, operationFactory);
		List<Integer> resultIndexes = bstFilterNode.process();
		List<T> results = new ArrayList<T>();
		for(Integer index:resultIndexes) {
			results.add((T) data[index]);
		}
		return results;
	}
	
	private void bstFilterNode(Filter filter, BSTFilterNode bstFilterNode, OperationFactory operationFactory) {
		if(filter.getOperator() != null) {
			if(filter.getField() == null || filter.getValue() == null) {
				throw new NullPointerException("Field name or Field value or both cannot be null.");
			}
			bstFilterNode.setOperation(operationFactory.getOperation(filter.getOperator()));
			bstFilterNode.setField(filter.getField());
			bstFilterNode.setValue(filter.getValue());
		} else if(filter.getLogicalOperator() != null) {
			if(filter.getLeftOperand() == null || filter.getRightOperand() == null) {
				throw new NullPointerException("Left operand or Right operand or both filters cannot be null.");
			}
			bstFilterNode.setOperation(operationFactory.getOperation(filter.getLogicalOperator()));
			bstFilterNode.setLeftOperand(new BSTFilterNode());
			bstFilterNode.setRightOperand(new BSTFilterNode());
			
			bstFilterNode(filter.getLeftOperand(), bstFilterNode.getLeftOperand(), operationFactory);
			bstFilterNode(filter.getRightOperand(), bstFilterNode.getRightOperand(), operationFactory);
		} else {
			throw new NullPointerException("Operator cannot be null.");
		}
	}
	
}
