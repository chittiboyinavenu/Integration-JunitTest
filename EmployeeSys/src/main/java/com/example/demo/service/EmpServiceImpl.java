package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmpRepository;
@Service
public class EmpServiceImpl implements EmpService
{
	@Autowired
	private EmpRepository empRepository;
	

	@Override
	public Employee addEmployee(Employee employee) 
	{
		return empRepository.save(employee);
	}


	@Override
	public List<Employee> getEmployee() {
		return empRepository.findAll();
	}


	@SuppressWarnings("deprecation")
	@Override
	public Employee getByIdEmployee(Integer employee) {
		 return empRepository.getById(employee);
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		return empRepository.save(employee);
	}


	@Override
	public void deleteEmployee(Integer id)
	{
		empRepository.deleteById(id);
		
	}


	


	


	


	

}
