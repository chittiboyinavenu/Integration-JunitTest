package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.Repository.EmpRepository;

@SpringBootTest(classes = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@EnableConfigurationProperties
@EntityScan("com.example.Entity")
@ComponentScan("com.example")
@EnableJpaRepositories(basePackageClasses=EmpRepository.class)
//@Configuration
class EmployeeSysApplicationTests {

	@Test
	void contextLoads() {
	}

}
