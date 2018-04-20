package com.stanrnd.jimdb.select;

import java.util.List;

public interface Operation {

	public List<Integer> eval(String column, Object value);
	
	public List<Integer> eval(List<Integer> list1, List<Integer> list2);
	
}
