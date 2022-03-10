package com.inventory.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.inventory.api.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = :userName and u.password = :password")
	User checkLogin(String userName, String password);

}

