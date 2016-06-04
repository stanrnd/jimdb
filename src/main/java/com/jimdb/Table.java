package com.jimdb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
	
	public void update(T bean, Filter filter) {
		try {
			Map<Field, Object> fieldMap = new HashMap<Field, Object>();
			Field[] fields = bean.getClass().getDeclaredFields();
			for(Field field:fields) {
				field.setAccessible(true);
		        Object value = field.get(bean);
		        if(value != null) {
		        	fieldMap.put(field, value);
		        }
			}
			List<T> beans = find(filter);
			for(T t:beans) {
				for(Entry<Field, Object> entry:fieldMap.entrySet()) {
					entry.getKey().set(t, entry.getValue());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Filter filter) {
		BSTFilterNode bstFilterNode = new BSTFilterNode();
		OperationFactory operationFactory = new OperationFactory(new OperationParam(lastRowId, maxNoOfRows, indexConfig, data, dataMapping, emptyIndexes));
		bstFilterNode(filter, bstFilterNode, operationFactory);
		List<Integer> resultIndexes = bstFilterNode.process();
		for(Integer index:resultIndexes) {
			data[index] = null;
			emptyIndexes.push(index);
		}
		for(String field:indexConfig.getColIndexes()) {
			Map<Object, List<Integer>> indexMap = dataMapping.get(field);
			for(Entry<Object, List<Integer>> entry:indexMap.entrySet()) {
				List<Integer> indexes = dataMapping.get(field).get(entry.getKey());
				for(int i=0;i<indexes.size();) {
					if(data[indexes.get(i)] == null) {
						indexes.remove(indexes.get(i));
					} else {
						++i;
					}
				}
			}
		}
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
