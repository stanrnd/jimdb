package com.jimdb;

import java.util.ArrayList;
import java.util.List;

public class NotEqualOperation<T> implements Operation<T> {
	
	private int lastRowId;

	public NotEqualOperation(int lastRowId) {
		this.lastRowId = lastRowId;
	}
	
	public List<Integer> eval(List<Integer> list1, List<Integer> list2) {
		int[] tempData = new int[lastRowId+1];
		for(int i=0;i<list1.size();i++) {
			tempData[list1.get(i)]++;
		}
		for(int i=0;i<list2.size();i++) {
			tempData[list2.get(i)]++;
		}
		List<Integer> list3 = new ArrayList<Integer>();
		for(int i=0;i<tempData.length;i++) {
			if(tempData[i] == 0) {
				list3.add(i);
			}
		}
		return list3;
	}

	public List eval(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

}
