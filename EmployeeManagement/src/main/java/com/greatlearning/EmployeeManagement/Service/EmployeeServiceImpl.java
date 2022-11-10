package com.greatlearning.EmployeeManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagement.Entity.Employee;
import com.greatlearning.EmployeeManagement.Exception.EmployeeNotFoundException;
import com.greatlearning.EmployeeManagement.Repos.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public List<Employee> getAllEmployeesWithTheseName(String firstname) {
		return employeeRepo.FindByFirstName(firstname);
	}

	@Override
	public List<Employee> getEmployeesCustomSortedByName(Direction direction) {
		return employeeRepo.findAll(Sort.by(direction, "firstname"));
	}

	@Override
	public Employee updateEmployee(int employee_id, Employee employeeDetails) throws EmployeeNotFoundException {

		Employee employee = employeeRepo.findById(employee_id)
				.orElseThrow(() -> new EmployeeNotFoundException(employee_id));

		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmail(employeeDetails.getEmail());
		Employee updatedEmployee = employeeRepo.save(employee);

		return updatedEmployee;
	}

}
