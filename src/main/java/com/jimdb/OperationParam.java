package com.jimdb;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class OperationParam {
	
	private int lastRowId;
	
	private int maxNoOfRows;

	private IndexConfig indexConfig;
	
	private Object[] data;
	
	private Map<String, TreeMap<Object, List<Integer>>> dataMapping;
	
	private Stack<Integer> emptyIndexes;

	public OperationParam(int lastRowId, int maxNoOfRows, IndexConfig indexConfig, Object[] data,
			Map<String, TreeMap<Object, List<Integer>>> dataMapping, Stack<Integer> emptyIndexes) {
		this.lastRowId = lastRowId;
		this.maxNoOfRows = maxNoOfRows;
		this.indexConfig = indexConfig;
		this.data = data;
		this.dataMapping = dataMapping;
		this.emptyIndexes = emptyIndexes;
	}

	public int getLastRowId() {
		return lastRowId;
	}

	public void setLastRowId(int lastRowId) {
		this.lastRowId = lastRowId;
	}

	public int getMaxNoOfRows() {
		return maxNoOfRows;
	}

	public void setMaxNoOfRows(int maxNoOfRows) {
		this.maxNoOfRows = maxNoOfRows;
	}

	public IndexConfig getIndexConfig() {
		return indexConfig;
	}

	public void setIndexConfig(IndexConfig indexConfig) {
		this.indexConfig = indexConfig;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public Map<String, TreeMap<Object, List<Integer>>> getDataMapping() {
		return dataMapping;
	}

	public void setDataMapping(Map<String, TreeMap<Object, List<Integer>>> dataMapping) {
		this.dataMapping = dataMapping;
	}

	public Stack<Integer> getEmptyIndexes() {
		return emptyIndexes;
	}

	public void setEmptyIndexes(Stack<Integer> emptyIndexes) {
		this.emptyIndexes = emptyIndexes;
	}

}
