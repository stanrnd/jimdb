package com.jimdb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jimdb.Filter;
import com.jimdb.IndexConfig;
import com.jimdb.Op;
import com.jimdb.Table;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Table<Employee> table = new Table<Employee>(100, new IndexConfig("department", "address"));
		fillData(table);
		
		//List<Employee> employees = table.find(new Filter(new Filter(new Filter("department", Op.EQ, "IT"), Op.OR, new Filter("department", Op.EQ, "QA")), Op.AND, new Filter("address", Op.EQ, "Bangalore")));
		
		
		long start = System.currentTimeMillis();
		List<Employee> employees = table.find(new Filter("department", Op.EQ, "IT"));
		System.out.println();
		for(Employee employee:employees) {
			System.out.println(employee);
		}
		
	}
	
	private static void fillData(Table<Employee> table) throws Exception {
		table.insert(new Employee("eid02", "Alan", "male", 20000, "HR", "Bangalore", "alan@gmail.com"));
		table.insert(new Employee("eid18", "Krish", "male", 10000, "IT", "Bangalore", "krish@gmail.com"));
		table.insert(new Employee("eid16", "Shiny", "female", 60000, "QA", "Chennai", "shiny@gmail.com"));
		table.insert(new Employee("eid11", "James", "male", 50000, "IT", "Bangalore", "james@gmail.com"));
		table.insert(new Employee("eid20", "Stalin", "male", 50000, "IT", "Chennai", "stalin@gmail.com"));
		table.insert(new Employee("eid12", "Anto", "male", 30000, "ADMIN", "Bangalore", "anto@gmail.com"));
		table.insert(new Employee("eid15", "Stanley", "male", 80000, "IT", "Chennai", "stanley@yahoo.com"));
		table.insert(new Employee("eid13", "Rosy", "female", 90000, "HR", "Bangalore", "rosy@yahoo.com"));
		table.insert(new Employee("eid06", "Jacklin", "female", 10000, "QA", "Bangalore", "jacklin@gmail.com"));
	}

}
