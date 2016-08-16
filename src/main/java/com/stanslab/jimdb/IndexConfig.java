package com.stanslab.jimdb;

public class IndexConfig {

	private String[] colIndexes;
	
	public IndexConfig(String ... colIndexes) {
		this.colIndexes = colIndexes;
	}

	public String[] getColIndexes() {
		return colIndexes;
	}

	public void setColIndexes(String[] colIndexes) {
		this.colIndexes = colIndexes;
	}
	
}
