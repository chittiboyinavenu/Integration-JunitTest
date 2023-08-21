package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.Entity.Employee;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RestTemplateEmpControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate testRestTemplate;
	
		
	@Test
	void testAddEmployee() {
		
			HttpHeaders headers = new HttpHeaders();
			Employee employee = Utility.getEmployee();
			HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);
			headers.setContentType(MediaType.APPLICATION_JSON);
			Employee actual = testRestTemplate
					.exchange("http://localhost:" + port + "/employee/addEmployee", HttpMethod.POST, requestEntity, Employee.class)
					.getBody();
			
		}



	@Test
	void testGetEmployee() {
		Employee employee =Utility.getEmployee();
		List<Employee> list=new ArrayList<Employee>();
		list.add(employee);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);
		
				
		ResponseEntity<Employee> responseEntity = testRestTemplate.exchange(
	            "http://localhost:" + port + "/employee/getEmployee", HttpMethod.GET, requestEntity, Employee.class);

	}

	@Test
	void testGetByIdEmployee() {
		HttpHeaders headers = new HttpHeaders();
		Employee employee = Utility.getEmployee();
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Employee> responseEntity = testRestTemplate.exchange(
				"http://localhost:" + port + "/employee/getEmployee/1", HttpMethod.GET, requestEntity, Employee.class);
		
		assertEquals(employee.toString().isEmpty(),responseEntity.toString().isEmpty());
		
	}

	@Test
	void testUpdateEmployee() {
		
		Employee employee = Utility.getEmployee();
	    employee.setName("shivam");
	
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Employee> responseEntity = testRestTemplate.exchange(
				"http://localhost:" + port + "/employee/updateEmployee/1", HttpMethod.PUT, requestEntity, Employee.class);
		assertEquals(employee.getId(),responseEntity.getBody().getId());
	}

	@Test
	void testDeleteEmployee() {
		HttpHeaders headers = new HttpHeaders();
		Employee employee = Utility.getEmployee();
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Employee> responseEntity = testRestTemplate.exchange(
				"http://localhost:" + port + "/employee/deleteEmployee/81", HttpMethod.DELETE, requestEntity, Employee.class);
		
		
	}

}
