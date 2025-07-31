package com.shiwansh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiwansh.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByName(String username);

}
