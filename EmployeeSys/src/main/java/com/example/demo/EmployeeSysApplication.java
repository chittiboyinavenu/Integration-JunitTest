package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.Repository.EmpRepository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@EnableConfigurationProperties
@EntityScan("com.example.demo.Entity")
@ComponentScan("com.example")
@EnableJpaRepositories(basePackageClasses=EmpRepository.class)
@Configuration
public class EmployeeSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSysApplication.class, args);
	}

}
