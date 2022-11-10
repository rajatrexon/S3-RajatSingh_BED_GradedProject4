package com.greatlearning.EmployeeManagement.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmployeeManagement.Entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query("FROM Employee WHERE firstName = ?1")
	List<Employee> FindByFirstName(String firstname);

	@Modifying
	@Query("update Employee u set u.firstname = ?1, u.lastname = ?2,u.email=?3 where u.id = ?4")
	void setEmployeeInfoById(String firstname, String lastname, String email, Integer userId);
}
