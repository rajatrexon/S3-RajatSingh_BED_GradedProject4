package com.greatlearning.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmployeeManagement.Entity.User;
import com.greatlearning.EmployeeManagement.Repos.UserRepository;

@RestController
@RequestMapping("/secure")
public class AdminController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/admin/all")
	public List<User> securedHello() {
		return userRepository.findAll();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/admin/delete")
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User with " + id + " got deleted";
	}

}
