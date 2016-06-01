package com.jimdb;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EqualOperation implements Operation {
	
	public List<Integer> eval(BSTFilterNode bstFilterNode) {
		OperationParam param = bstFilterNode.getOperationParam();
		Map<String, TreeMap<Object, List<Integer>>> dataMapping = param.getDataMapping();
		return dataMapping.get(bstFilterNode.getColumn()).get(bstFilterNode.getValue());
	}

}
