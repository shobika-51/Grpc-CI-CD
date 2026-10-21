package com.example.employeeservice.Grpc;

import java.util.List;

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
import com.example.employeeservice.Entity.EmployeeEntity;
import com.example.employeeservice.Repository.EmployeeRepository;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class EmployeeGrpcService    extends EmployeeServiceGrpc.EmployeeServiceImplBase{
    private final EmployeeRepository repository;
    public EmployeeGrpcService(EmployeeRepository repository) {
        this.repository = repository;
    }
    @Override
    public void createEmployee(CreateEmployeeRequest request,StreamObserver<EmployeeResponse> responseObserver) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        EmployeeEntity saved =repository.save(employee);
        Employee grpcEmployee =Employee.newBuilder()
                        .setId(saved.getId())
                        .setName(saved.getName())
                        .setEmail(saved.getEmail())
                        .setDepartment(saved.getDepartment())
                        .setSalary(saved.getSalary())
                        .build();
        EmployeeResponse response =EmployeeResponse.newBuilder()
                        .setEmployee(grpcEmployee)
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getEmployee(GetEmployeeRequest request,StreamObserver<EmployeeResponse> responseObserver) {
        EmployeeEntity employee = repository.findById(request.getId()).orElse(null);
        if (employee == null) {
            responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Employee not found")
                            .asRuntimeException());
            return;
        }
        Employee grpcEmployee = Employee.newBuilder()
                        .setId(employee.getId())
                        .setName(employee.getName())
                        .setEmail(employee.getEmail())
                        .setDepartment(employee.getDepartment())
                        .setSalary(employee.getSalary())
                        .build();
        EmployeeResponse response = EmployeeResponse.newBuilder()
                        .setEmployee(grpcEmployee)
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getAllEmployees( GetAllEmployeesRequest request, StreamObserver<EmployeeListResponse> responseObserver) {
        List<EmployeeEntity> employees = repository.findAll();
        EmployeeListResponse.Builder response = EmployeeListResponse.newBuilder();
        for (EmployeeEntity employee : employees) {
            Employee grpcEmployee =Employee.newBuilder()
                            .setId(employee.getId())
                            .setName(employee.getName())
                            .setEmail(employee.getEmail())
                            .setDepartment(employee.getDepartment())
                            .setSalary(employee.getSalary())
                            .build();
            response.addEmployees(grpcEmployee);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    @Override
    public void updateEmployee(UpdateEmployeeRequest request,StreamObserver<EmployeeResponse> responseObserver) {
        EmployeeEntity employee = repository.findById(request.getId())
                        .orElse(null);
        if (employee == null) {
            responseObserver.onError( io.grpc.Status.NOT_FOUND
                            .withDescription("Employee not found")
                            .asRuntimeException());
            return;
        }
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        EmployeeEntity updated = repository.save(employee);
        Employee grpcEmployee =Employee.newBuilder()
                        .setId(updated.getId())
                        .setName(updated.getName())
                        .setEmail(updated.getEmail())
                        .setDepartment(updated.getDepartment())
                        .setSalary(updated.getSalary())
                        .build();
        EmployeeResponse response = EmployeeResponse.newBuilder()
                        .setEmployee(grpcEmployee)
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteEmployee( DeleteEmployeeRequest request,StreamObserver<DeleteEmployeeResponse> responseObserver) {
        boolean exists = repository.existsById(request.getId());
        if (!exists) {
            DeleteEmployeeResponse response = DeleteEmployeeResponse.newBuilder()
                            .setSuccess(false)
                            .setMessage("Employee not found")
                            .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        repository.deleteById(request.getId());
        DeleteEmployeeResponse response = DeleteEmployeeResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Employee deleted successfully")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}