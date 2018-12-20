package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
