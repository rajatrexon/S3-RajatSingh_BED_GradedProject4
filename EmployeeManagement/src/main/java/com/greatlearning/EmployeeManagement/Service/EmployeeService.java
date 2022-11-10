package com.greatlearning.EmployeeManagement.Service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.greatlearning.EmployeeManagement.Entity.Employee;
import com.greatlearning.EmployeeManagement.Exception.EmployeeNotFoundException;

public interface EmployeeService {

	public List<Employee> getAllEmployeesWithTheseName(String firstname);

	List<Employee> getEmployeesCustomSortedByName(Direction direction);

	public Employee updateEmployee(int employee_id, Employee employeeDetails) throws EmployeeNotFoundException;

}