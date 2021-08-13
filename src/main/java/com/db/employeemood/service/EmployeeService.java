package com.db.employeemood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.employeemood.model.Employee;
import com.db.employeemood.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployee() {
		List<Employee> employee = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee1 -> employee.add(employee1));
		return employee;
	}

	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findById(email).get();
	}
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

}
