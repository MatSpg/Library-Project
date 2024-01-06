package com.example.library.service;

import java.util.Optional;

import com.example.library.entity.User;

public interface UserService {
	
	public Iterable<User> getAll();
	public Optional<User> getById(int id);
	public User create(User user);
	public Boolean delete(int id);
	
	
}
