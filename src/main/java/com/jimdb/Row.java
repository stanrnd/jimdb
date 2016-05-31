package com.jimdb;

public class Row<T> {
	
	private long id;
	
	private T value;

	public Row(long id, T value) {
		this.id = id;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
}
