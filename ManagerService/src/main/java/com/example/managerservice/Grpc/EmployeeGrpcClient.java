package com.example.managerservice.Grpc;

import org.springframework.stereotype.Service;

import com.example.employee.grpc.CreateEmployeeRequest;
import com.example.employee.grpc.DeleteEmployeeRequest;
import com.example.employee.grpc.DeleteEmployeeResponse;
import com.example.employee.grpc.Employee;
import com.example.employee.grpc.EmployeeListResponse;
import com.example.employee.grpc.EmployeeResponse;
import com.example.employee.grpc.EmployeeServiceGrpc;
import com.example.employee.grpc.GetAllEmployeesRequest;
import com.example.employee.grpc.GetEmployeeRequest;
import com.example.employee.grpc.UpdateEmployeeRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class EmployeeGrpcClient {

    private final EmployeeServiceGrpc.EmployeeServiceBlockingStub blockingStub;

    public EmployeeGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder .forAddress("localhost", 9090)
                        .usePlaintext()
                        .build();
        blockingStub = EmployeeServiceGrpc .newBlockingStub(channel);
    }

    public Employee createEmployee( String name,String email,String department, double salary) {
        CreateEmployeeRequest request = CreateEmployeeRequest.newBuilder()
                        .setName(name)
                        .setEmail(email)
                        .setDepartment(department)
                        .setSalary(salary)
                        .build();
        EmployeeResponse response = blockingStub.createEmployee(request);
        return response.getEmployee();
    }

    public Employee getEmployee(Long id) {
        GetEmployeeRequest request = GetEmployeeRequest.newBuilder()
                        .setId(id)
                        .build();
        EmployeeResponse response = blockingStub.getEmployee(request);
        return response.getEmployee();
    }

    public EmployeeListResponse getAllEmployees() {

        GetAllEmployeesRequest request = GetAllEmployeesRequest.newBuilder()
                        .build();
        return blockingStub.getAllEmployees(request);
    }

    public Employee updateEmployee( Long id, String name, String email,String department, double salary) {
        UpdateEmployeeRequest request = UpdateEmployeeRequest.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .setDepartment(department)
                        .setSalary(salary)
                        .build();
        EmployeeResponse response = blockingStub.updateEmployee(request);
        return response.getEmployee();
    }

    public DeleteEmployeeResponse deleteEmployee(Long id) {
        DeleteEmployeeRequest request =DeleteEmployeeRequest.newBuilder()
                        .setId(id)
                        .build();
        return blockingStub.deleteEmployee(request);
    }
}