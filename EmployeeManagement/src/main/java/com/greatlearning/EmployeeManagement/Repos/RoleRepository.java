package com.greatlearning.EmployeeManagement.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.EmployeeManagement.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
