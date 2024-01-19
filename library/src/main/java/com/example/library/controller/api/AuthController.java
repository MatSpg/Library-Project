package com.example.library.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.AuthResponseDto;
import com.example.library.dto.LoginDto;
import com.example.library.entity.UserEntity;
import com.example.library.jwt.JwtTokenGenerator;
import com.example.library.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenGenerator jwtTokenGenerator) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenGenerator = jwtTokenGenerator;
	}
	
	
	
	@PostMapping(value = "/register", consumes = "application/json")
	public ResponseEntity<String> register(@Valid @RequestBody UserEntity user) {
		if (userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<String>("Registrazione non andata a buon fine del utente "+ user.getUsername()+" l'username è già esistente.", HttpStatus.OK);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userService.create(user);
		return new ResponseEntity<String>("Registrazione effettuata del utente "+ user.getUsername(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/login", consumes = "application/json")
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenGenerator.generateToken(authentication);
		
		return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
	}
}
