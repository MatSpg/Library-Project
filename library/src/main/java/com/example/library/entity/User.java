package com.example.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "biography")
	private String biography;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "birthdate")
	private String birthdate;
	
	@Column(name = "favourite_books")
	private String favourite_books;

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getFavourite_books() {
		return favourite_books;
	}

	public void setFavourite_books(String favourite_books) {
		this.favourite_books = favourite_books;
	}
	
	public User() {}
	
	public User(String username, String password, String name, String surname, String biography, String image, String birthdate, String favourite_books) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.biography = biography;
		this.image = image;
		this.birthdate = birthdate;
		this.favourite_books = favourite_books;
	}
}
