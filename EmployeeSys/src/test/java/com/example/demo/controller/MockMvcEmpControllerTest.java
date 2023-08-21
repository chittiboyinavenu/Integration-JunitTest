package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.Entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcEmpControllerTest 
{
	
	@Autowired
	MockMvc mockMvc;
	
	private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
	
	@Test
	void testAddEmployee() throws Exception {
		Employee employee = Utility.getEmployee();
		String requestBody = new ObjectMapper().writeValueAsString(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(MockMvcRequestBuilders.post("/employee/addEmployee").content(requestBody).headers(headers))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(result -> {
					String response = result.getResponse().getContentAsString();
					Employee actual = new ObjectMapper().readValue(response, Employee.class);
				});
	}
	
	@Test
	void testGetByIdEmployee() throws Exception {
		Employee employee = Utility.getEmployee();
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/getEmployee/{id}", 6)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(6))) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(employee.getName()))) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(employee.getEmail())));
	}
	
	@Test
	void testGetAllEmployee() throws Exception
	{
		Employee employees = Utility.getEmployee();
		List<Employee> list = new ArrayList<>();
		list.add(employees);
		   mockMvc.perform(MockMvcRequestBuilders.get("/employee/getEmployee")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
	               
     }
	
	

	@Test
	void testUpdateEmployee() throws Exception {
		Employee employee = Utility.getEmployee();
		employee.setName("venkateswara");
		
		mockMvc.perform(MockMvcRequestBuilders.put("/employee/updateEmployee/{id}",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee)) 
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("venkateswara")));

		
	}
	
	@Test
	void testDeleteEmployee() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/deleteEmployee/{id}",3).headers(headers))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
