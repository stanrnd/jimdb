package com.stanrnd.jimdb.select.operation;

import java.util.ArrayList;
import java.util.List;

import com.stanrnd.jimdb.select.Operation;
import com.stanrnd.jimdb.select.OperationParam;

public class AndOperation implements Operation {
	
	private OperationParam operationParam;
	
	public AndOperation(OperationParam operationParam) {
		this.operationParam = operationParam;
	}

	public List<Integer> eval(String column, Object value) {
		
		return null;
	}

	public List<Integer> eval(List<Integer> list1, List<Integer> list2) {
		int[] tempData = new int[operationParam.getLastRowId()+1];
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
	
}
