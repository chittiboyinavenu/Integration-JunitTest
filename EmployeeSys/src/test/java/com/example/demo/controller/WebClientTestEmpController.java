package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec;
import org.springframework.test.web.reactive.server.WebTestClient.RequestBodySpec;

import com.example.demo.Entity.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class WebClientTestEmpController {

	@LocalServerPort 
	 private int port;
	 
	 @Autowired
	 WebTestClient webTestClient;
	 
	 @Test
	    void testAddEmployee() {
	        Employee employee = Utility.getEmployee();
	        Employee actual = webTestClient.post()
	                .uri("http://localhost:" + port + "/employee/addEmployee")
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(employee)
	                .exchange()
	                .expectStatus().isOk()
	                .expectBody(Employee.class)
	                .returnResult()
	                .getResponseBody();
	        assertEquals(employee.getName(),actual.getName());
	}
	 
	 @Test
	    void testGetAllEmployees() {
		 Employee employee =Utility.getEmployee();
			List<Employee>list=new ArrayList<Employee>();
			list.add(employee);
			webTestClient.get()
            .uri("http://localhost:" + port + "/employee/getEmployee")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() 
            .expectStatus().isOk()
            .expectBodyList(Employee.class)
            .consumeWith(response -> {
                List<Employee> expectedEmployees = response.getResponseBody();
                assertEquals(employee.getName().isEmpty(),expectedEmployees.isEmpty());
            });
	 }
	 
	 @Test
	    void testGetEmployeeById() {
	      
	        Employee employee = Utility.getEmployee();

	        Employee actual = webTestClient.get()
	            .uri("http://localhost:" + port + "/employee/getEmployee/{id}", 3) 
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(Employee.class)
	            .returnResult()
                .getResponseBody();
	        assertEquals(employee.getName(),actual.getName());
	    }
	 
	 @Test
	    void testUpdateEmployee() {
	        Employee employeeToUpdate = Utility.getEmployee();
	        employeeToUpdate.setName("shivan");
	        
	        webTestClient.put()
	            .uri("http://localhost:" + port + "/employee/updateEmployee/{id}", 2) 
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(employeeToUpdate)
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(Employee.class)
	            .consumeWith(response -> {
	                Employee updatedEmployee = response.getResponseBody();
	                assertThat(updatedEmployee).isNotNull();
	                assertThat(updatedEmployee.getName()).isEqualTo("shivan");
	            });
	    }
		 
	 @Test
	    void testDeleteEmployee() {
	        Employee employeeToDelete = Utility.getEmployee();

	        webTestClient.delete()
	            .uri("http://localhost:" + port + "/employee/deleteEmployee/{id}", 3) // Corrected URI format
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(void.class)
	            .consumeWith(response -> {
	            });
	    }
}
