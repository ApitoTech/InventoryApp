package com.inventory.api.controller;

import com.inventory.api.entity.Item;
import com.inventory.api.entity.User;
import com.inventory.api.service.ItemService;
import com.inventory.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class loginController {
	 @Autowired
	 private UserService userService;
    
	 @PostMapping("/login")
   public User addItem(@RequestBody User user) {
       return userService.checkLogin(user);
   }

}
