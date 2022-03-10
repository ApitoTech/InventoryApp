package com.inventory.api.service;


import com.inventory.api.entity.User;
import com.inventory.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	 @Autowired
	 private UserRepository repository; 
	 
	public User checkLogin(User user) {
		return repository.checkLogin(user.getUserName(),user.getPassword());
	}

}
