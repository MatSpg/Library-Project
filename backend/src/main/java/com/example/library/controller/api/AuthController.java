package com.example.library.controller.api;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.configuration.CustomUserDetailsService;
import com.example.library.dto.AuthResponseDto;
import com.example.library.dto.LoginDto;
import com.example.library.entity.UserEntity;
import com.example.library.jwt.JwtTokenGenerator;
import com.example.library.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtTokenGenerator jwtTokenGenerator;
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenGenerator jwtTokenGenerator, CustomUserDetailsService customUserDetailsService) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenGenerator = jwtTokenGenerator;
		this.customUserDetailsService = customUserDetailsService;
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
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getUsername());
		
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		} catch (BadCredentialsException e) {
			
			throw new BadCredentialsException("Nome utente o password incorretti.");
			
		} catch (DisabledException e) {
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Utente non trovato o inattivo.");
			return null;
		}
		
		String token = jwtTokenGenerator.generateToken(userDetails.getUsername());
		
		return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
	}
}
