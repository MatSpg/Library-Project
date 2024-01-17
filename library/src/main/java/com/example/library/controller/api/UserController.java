package com.example.library.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.library.entity.UserEntity;
import com.example.library.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	@GetMapping("/all")
	public Iterable<UserEntity> getAllUser() {
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable int id) {
		return userService.getById(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public Boolean deleteUser(@PathVariable int id) {
		Boolean foundUser = userService.delete(id);
		
		if (foundUser == false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato.");
		}
		
		return foundUser;
	}
}
