package com.example.library.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.library.entity.UserEntity;
import com.example.library.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (userService.existsByUsername(username)) {
			UserEntity user = userService.findByUsername(username);
			SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(user.getRole());
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(userAuthority);
			
			return new User(user.getUsername(), user.getPassword(), authorities);
		} else {
			throw new BadCredentialsException("Nome utente non trovato o inesistente: "+username);
		}
	}
	
}
