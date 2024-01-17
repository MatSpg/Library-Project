package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.UserEntity;

@Repository
public interface UserRepository extends 	JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);
	
}
