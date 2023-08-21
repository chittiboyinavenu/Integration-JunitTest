package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.Entity.Employee;
import com.example.demo.service.EmpService;

@SpringBootTest
class EmpControllerTest {

	@InjectMocks EmpController empController;
	
	@Mock
	 EmpService empService;
	
	@Mock Employee employee;
	
	public static Employee getEmployee()
	{
		Employee employee = new Employee();
		employee.setAddress("bngl");
		employee.setEmail("venu@gmail.com");
		employee.setName("venu");
		employee.setSalary(1000);
		return employee;
	}
	
	@Test
	void testAddEmployee() 
	{
		Employee employee =getEmployee();		
		when(empService.addEmployee(employee)).thenReturn(employee);
		assertEquals(empController.addEmployee(employee).getName(), employee.getName());
		
		
	}

	@Test
	void testGetByIdEmployee() 
	{
		Employee employee =getEmployee();
		Mockito.when(empService.getByIdEmployee(1)).thenReturn(employee);
		assertEquals(empController.getByIdEmployee(1).getName(), employee.getName());
		
	}
	
	@Test
	void testGetAllEmployee() 
	{
		Employee employee =getEmployee();
		List<Employee>list=new ArrayList<Employee>();
		list.add(employee);
		Mockito.when(empService.getEmployee()).thenReturn(list);
		assertEquals(empController.getEmployee().isEmpty(),list.isEmpty());
	}

	@Test
	void testUpdateEmployee()
	{
		Employee employee =getEmployee();
		Mockito.when(empService.updateEmployee(employee)).thenReturn(employee);
		assertEquals(empController.updateEmployee(employee).getName(),employee.getName());

	}

	@Test
	void testDeleteEmployee()
	{
		Employee employee =getEmployee();
		doNothing().when(empService).deleteEmployee(1);
		empController.deleteEmployee(1);

	}

}
