package com.example.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;

@Service("dbUserService")
public class DbUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> getById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User create(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Boolean delete(int id) {
		Optional<User> foundUser = userRepository.findById(id);
		
		if (foundUser.isEmpty()) {
			return false;
		}
		
		userRepository.delete(foundUser.get());
		return true;
	}
	
}
