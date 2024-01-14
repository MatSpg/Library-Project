package com.example.library.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.library.entity.User;
import com.example.library.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	@GetMapping("/all")
	public Iterable<User> getAllUser() {
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
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
