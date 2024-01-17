package com.example.library.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.library.entity.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	@Qualifier("dbUserService")
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		UserEntity user = userService.findByUsername(username);
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(user.getRole());
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(userAuthority);
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
	
}
