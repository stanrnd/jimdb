package com.jimdb;

import java.util.ArrayList;
import java.util.List;

public class EqualOperation implements Operation {
	
	public List<Integer> eval(BSTFilterNode bstFilterNode) {
		List<Integer> list1 = bstFilterNode.getLeftOperand().getResult();
		List<Integer> list2 = bstFilterNode.getRightOperand().getResult();
		
		int[] tempData = new int[bstFilterNode.getOperationParam().getLastRowId()+1];
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
