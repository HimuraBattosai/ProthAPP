package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	
}
