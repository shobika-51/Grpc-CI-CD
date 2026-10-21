package com.example.managerservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.grpc.DeleteEmployeeResponse;
import com.example.employee.grpc.Employee;
import com.example.employee.grpc.EmployeeListResponse;
import com.example.managerservice.Grpc.EmployeeGrpcClient;

@Service
public class ManagerService {
	@Autowired
	private EmployeeGrpcClient employeeGrpcClient;
	    public Employee createEmployee(
            String name,
            String email,
            String department,
            double salary) {

        try {

            Employee employee =
                    employeeGrpcClient.createEmployee(
                            name,
                            email,
                            department,
                            salary
                    );

            System.out.println("Employee Created Successfully");
            System.out.println("ID         : " + employee.getId());
            System.out.println("Name       : " + employee.getName());
            System.out.println("Email      : " + employee.getEmail());
            System.out.println("Department : " + employee.getDepartment());
            System.out.println("Salary     : " + employee.getSalary());

            return employee;

        } catch (Exception ex) {

            System.out.println(
                    "Employee create failed: "
                    + ex.getMessage());

            throw ex;
        }
    }

   public Employee getEmployee(Long id) {
        try {
            Employee employee =employeeGrpcClient.getEmployee(id);
            System.out.println("Employee Retrieved Successfully");
            System.out.println("ID         : " + employee.getId());
            System.out.println("Name       : " + employee.getName());
            System.out.println("Email      : " + employee.getEmail());
            System.out.println("Department : " + employee.getDepartment());
            System.out.println("Salary     : " + employee.getSalary());
            return employee;
        } catch (Exception ex) {
            System.out.println("Employee get failed: " + ex.getMessage());

            throw ex;
        }
    }

    public EmployeeListResponse getAllEmployees() {
        try {
            EmployeeListResponse response = employeeGrpcClient.getAllEmployees();
            System.out.println("Total Employees: "+ response.getEmployeesCount());
            for (Employee employee : response.getEmployeesList()) {
                System.out.println("ID         : " + employee.getId());
                System.out.println( "Name       : " + employee.getName());
                System.out.println( "Email      : " + employee.getEmail());
                System.out.println( "Department : " + employee.getDepartment());
                System.out.println("Salary     : " + employee.getSalary());
            }
            return response;
        } catch (Exception ex) {
            System.out.println( "Get all employees failed: " + ex.getMessage());
            throw ex;
        }
    }
    public Employee updateEmployee( Long id, String name, String email, String department, double salary) {
        try {
            Employee employee = employeeGrpcClient.updateEmployee( id,name, email,department, salary);
            System.out.println("Employee Updated Successfully");
            System.out.println("ID         : " + employee.getId());
            System.out.println("Name       : " + employee.getName());
            System.out.println("Email      : " + employee.getEmail());
            System.out.println("Department : " + employee.getDepartment());
            System.out.println("Salary     : " + employee.getSalary());
            return employee;
        } catch (Exception ex) {
            System.out.println( "Employee update failed: "+ ex.getMessage());

            throw ex;
        }
    }
    public DeleteEmployeeResponse deleteEmployee(Long id) {
        try {
            DeleteEmployeeResponse response = employeeGrpcClient.deleteEmployee(id);
            System.out.println( "Delete Success: " + response.getSuccess());
            System.out.println( "Message: "+ response.getMessage());
            return response;
        } catch (Exception ex) {
            System.out.println( "Employee delete failed: "+ ex.getMessage());
            throw ex;
        }
    }
}
