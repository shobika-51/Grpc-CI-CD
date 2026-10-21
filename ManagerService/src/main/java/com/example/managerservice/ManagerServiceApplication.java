package com.example.managerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.employee.grpc.DeleteEmployeeResponse;
import com.example.employee.grpc.Employee;
import com.example.employee.grpc.EmployeeListResponse;
import com.example.managerservice.Service.ManagerService;

@SpringBootApplication
public class ManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerServiceApplication.class, args);
	}
	
	
//create
//	@Bean
//	CommandLineRunner testManagerService( ManagerService managerService) {
//	    return args -> {
//	        Employee employee = managerService.createEmployee(
//	                        "Shobika",
//	                        "shobika@gmail.com",
//	                        "IT",
//	                        50000
//	                );
//      
//	    };
//	}
//	
	//get by id
//	  @Bean
//	    CommandLineRunner testManagerService(ManagerService managerService) {
//	        return args -> {
//               Employee employee =managerService.getEmployee(2L);
//	         };
//	    }
	
	//update
//	@Bean
//	CommandLineRunner testManagerService( ManagerService managerService) {
//	    return args -> {
//	        Employee employee = managerService.updateEmployee(
//	                        3L,
//	                        "Shobika",
//	                        "shobika@gmail.com",
//	                        "CSE",
//	                        50000
//	                );
//	        System.out.println("Updated Employee: "+ employee.getName());
//	    };
//	}
	
	//get all
	
//	@Bean
//	CommandLineRunner testManagerService(ManagerService managerService) {
//	    return args -> {
//	        EmployeeListResponse response = managerService.getAllEmployees();
//	        System.out.println( "Total Employees: "+ response.getEmployeesCount());
//	    };
//	}
	
	//delete
	
//	@Bean
//	CommandLineRunner testManagerService(ManagerService managerService) {
//	    return args -> {
//	        DeleteEmployeeResponse response = managerService.deleteEmployee(4L);
//	       
//	    };
//	
//	}
}
