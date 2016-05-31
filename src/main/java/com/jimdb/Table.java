package com.jimdb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Table<T> {
	
	private int lastRowId;
	
	private int maxNoOfRows;

	private IndexConfig indexConfig;
	
	private Object[] data;
	
	private Map<String, TreeMap<Object, List<Integer>>> dataMapping;
	
	private Stack<Integer> emptyIndexes;
	
	public Table(int maxNoOfRows, IndexConfig indexConfig) {
		this.data = new Object[maxNoOfRows];
		this.indexConfig = indexConfig;
		this.emptyIndexes = new Stack<Integer>();
		this.dataMapping = new HashMap<String, TreeMap<Object,List<Integer>>>();
		for(String col:indexConfig.getColIndexes()) {
			dataMapping.put(col, new TreeMap<Object, List<Integer>>());
		}
	}
	
	public void insert(T row) throws Exception {
		int emptyRowId = findEmptyRowId();
		data[emptyRowId] = row;
		for(String col:indexConfig.getColIndexes()) {
			Field field = row.getClass().getDeclaredField(col);
			field.setAccessible(true);
	        Object value = field.get(row);
	        List<Integer> rows = dataMapping.get(col).get(value);
			if(rows == null) {
				dataMapping.get(col).put(value, new ArrayList<Integer>());
			}
			dataMapping.get(col).get(value).add(emptyRowId);
		}
	}
	
	public List<T> select() {
		
		return null;
	}
	
	private List<Integer> and(List<Integer> list1, List<Integer> list2) {
		int[] tempData = new int[lastRowId+1];
		for(int i=0;i<list1.size();i++) {
			tempData[list1.get(i)]++;
		}
		for(int i=0;i<list2.size();i++) {
			tempData[list2.get(i)]++;
		}
		List<Integer> list3 = new ArrayList<Integer>();
		for(int i=0;i<tempData.length;i++) {
			if(tempData[i] == 2) {
				list3.add(i);
			}
		}
		return list3;
	}
	
	private List<Integer> or(List<Integer> list1, List<Integer> list2) {
		int[] tempData = new int[lastRowId+1];
		for(int i=0;i<list1.size();i++) {
			tempData[list1.get(i)]++;
		}
		for(int i=0;i<list2.size();i++) {
			tempData[list2.get(i)]++;
		}
		List<Integer> list3 = new ArrayList<Integer>();
		for(int i=0;i<tempData.length;i++) {
			if(tempData[i] > 0) {
				list3.add(i);
			}
		}
		return list3;
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
	
	public List<T> find(Filter filter) {
		BSTFilterNode bstFilterNode = new BSTFilterNode();
		OperationParam operationParam = new OperationParam(lastRowId, maxNoOfRows, indexConfig, data, dataMapping, emptyIndexes);
		bstFilterNode(filter, bstFilterNode, operationParam);
		BSTFilterNode resultBSTFilterNode = bstFilterNode.process();
		List<Integer> results = resultBSTFilterNode.getResult();
		
		System.out.println(bstFilterNode);
		return null;
	}
	
	private void bstFilterNode(Filter filter, BSTFilterNode bstFilterNode, OperationParam operationParam) {
		bstFilterNode.setOperationParam(operationParam);
		bstFilterNode.setOperator(filter.getOperator());
		if(filter.getLeftOperand() != null && filter.getRightOperand() != null) {
			bstFilterNode.setLeftOperand(new BSTFilterNode());
			bstFilterNode.setRightOperand(new BSTFilterNode());
			
			bstFilterNode(filter.getLeftOperand(), bstFilterNode.getLeftOperand(), operationParam);
			bstFilterNode(filter.getRightOperand(), bstFilterNode.getRightOperand(), operationParam);
		}
		if(filter.getColumn() != null && filter.getValue() != null) {
			bstFilterNode.setColumn(filter.getColumn());
			bstFilterNode.setValue(filter.getValue());
		}
	}
	
}
