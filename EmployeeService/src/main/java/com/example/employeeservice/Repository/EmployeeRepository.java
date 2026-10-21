package com.example.employeeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeservice.Entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

}
