package com.greatlearning.EmployeeManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagement.Entity.User;
import com.greatlearning.EmployeeManagement.Repos.UserRepository;
import com.greatlearning.EmployeeManagement.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.getUserByUsername(username);
//		MyUserDetails userDetails = null;
//		if (user != null) {
//			userDetails = new MyUserDetails();
//			userDetails.setUser(user);
//		} else {
//			throw new UsernameNotFoundException("User not exist with name : " + username);
//		}
//		return userDetails;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Could not find user with given name");
		return new MyUserDetails(user);
	}
}
