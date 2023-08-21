package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/employee")
public class EmpController
{
	@Autowired
	private EmpService empService;
	
	@PostMapping(value = "/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return empService.addEmployee(employee);
	}
	
	@GetMapping("/getEmployee")
	public List<Employee> getEmployee()
	{
		return empService.getEmployee();
	}
	
	@GetMapping("/getEmployee/{id}")
	public Employee getByIdEmployee(@PathVariable Integer id)
	{
		return empService.getByIdEmployee(id);
	}
	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		return empService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Integer id)
	{
		empService.deleteEmployee(id);
	}
}
