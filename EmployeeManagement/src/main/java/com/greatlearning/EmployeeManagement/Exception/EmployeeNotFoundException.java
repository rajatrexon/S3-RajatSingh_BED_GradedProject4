package com.greatlearning.EmployeeManagement.Exception;

public class EmployeeNotFoundException extends Exception {

	private int employee_id;

	public EmployeeNotFoundException(int employee_id) {
		super(String.format("Employee is not found with id : '%s'", employee_id));
	}

}
