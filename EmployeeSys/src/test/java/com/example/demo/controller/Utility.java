package com.example.demo.controller;

import com.example.demo.Entity.Employee;

public class Utility {

	public static Employee getEmployee()
	{
		Employee employee = new Employee();
		employee.setId(10);
		employee.setAddress("bngl");
		employee.setEmail("venu@gmail.com");
		employee.setName("venu");
		employee.setSalary(1000);
		return employee;
	}

	
}
