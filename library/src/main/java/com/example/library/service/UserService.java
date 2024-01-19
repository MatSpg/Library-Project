package com.example.library.service;

import java.util.Optional;

import com.example.library.entity.UserEntity;

public interface UserService{
	
	public Iterable<UserEntity> getAll();
	public Optional<UserEntity> getById(int id);
	public UserEntity findByUsername(String username);
	public boolean existsByUsername(String username);
	public UserEntity create(UserEntity user);
	public Boolean delete(int id);
	
	
}
