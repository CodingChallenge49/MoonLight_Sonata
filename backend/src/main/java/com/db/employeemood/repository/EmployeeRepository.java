package com.db.employeemood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.employeemood.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
