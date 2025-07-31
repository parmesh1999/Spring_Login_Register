package com.shiwansh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiwansh.model.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

	UserRole findByUserId(int id);

}
