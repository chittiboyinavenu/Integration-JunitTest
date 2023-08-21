package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;

@Service
public interface EmpService {

	Employee addEmployee(Employee employee);

	List<Employee> getEmployee();

	Employee getByIdEmployee(Integer employee);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Integer id);


	
}
