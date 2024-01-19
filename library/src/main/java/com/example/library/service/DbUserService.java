package com.example.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.UserEntity;
import com.example.library.repository.UserRepository;

@Service("dbUserService")
public class DbUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Iterable<UserEntity> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<UserEntity> getById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public UserEntity create(UserEntity user) {
		return userRepository.save(user);
	}
	
	@Override
	public Boolean delete(int id) {
		Optional<UserEntity> foundUser = userRepository.findById(id);
		
		if (foundUser.isEmpty()) {
			return false;
		}
		
		userRepository.delete(foundUser.get());
		return true;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}
	
}
