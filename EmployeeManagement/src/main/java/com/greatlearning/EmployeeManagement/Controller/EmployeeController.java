package com.greatlearning.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmployeeManagement.Entity.Employee;
import com.greatlearning.EmployeeManagement.Exception.EmployeeNotFoundException;
import com.greatlearning.EmployeeManagement.Repos.EmployeeRepo;
import com.greatlearning.EmployeeManagement.Service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	EmployeeService employeeservice;

	// Get All Employees
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

// Create a new Employee
	@PostMapping("/save")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee addEmployee(@Validated @RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

// Get a Single Employee by Id
	@GetMapping("/getEmployee/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public Employee getEmployeeById(@PathVariable(value = "id") int employee_id) throws EmployeeNotFoundException {
		return employeeRepo.findById(employee_id).orElseThrow(() -> new EmployeeNotFoundException(employee_id));
	}

//// Update a Employee
//	@PutMapping("/updateEmployee/{id}")
//	@Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public Employee updateEmployee(@PathVariable(value = "id") int employee_id,
//			@Validated @RequestBody Employee employeeDetails,Model m) throws EmployeeNotFoundException {
//
//		Employee employee = employeeRepo.findById(employee_id)
//				.orElseThrow(() -> new EmployeeNotFoundException(employee_id));
//         m.
//		employee.setFirstname(employeeDetails.getFirstname());
//		employee.setLastname(employeeDetails.getLastname());
//		employee.setEmail(employeeDetails.getEmail());
//		Employee updatedEmployee = employeeRepo.save(employee);
//
//		return updatedEmployee;
//	}

	// Update a Employee
	@PutMapping("/updateEmployee/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Employee updateEmployee(@PathVariable(value = "id") int employee_id,
			@Validated @RequestBody Employee employeeDetails) throws EmployeeNotFoundException {

		return this.employeeservice.updateEmployee(employee_id, employeeDetails);

	}

// Delete a Employee
	@DeleteMapping("/deleteEmployee/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteEmployee(@PathVariable(value = "id") int employee_id) throws EmployeeNotFoundException {

		Employee employee = employeeRepo.findById(employee_id)
				.orElseThrow(() -> new EmployeeNotFoundException(employee_id));

		employeeRepo.deleteById(employee_id);

		return "Employee with id " + employee_id + " got deleted";
	}

	@GetMapping("/getAllEmployeesWithTheseName/{firstname}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public List<Employee> getAllEmployeesWithTheseName(String firstname) {
		return employeeservice.getAllEmployeesWithTheseName(firstname);
	}

	@GetMapping("/getEmployeesCustomSortedByName/{direction}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public List<Employee> getEmployeesCustomSortedByName(Direction direction) {
		return employeeservice.getEmployeesCustomSortedByName(direction);
	}

}
