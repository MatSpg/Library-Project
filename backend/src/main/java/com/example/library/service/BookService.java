package com.example.library.service;

import java.util.Optional;

import com.example.library.entity.Book;

public interface BookService {

	public Iterable<Book> getAll();
	public Optional<Book> getById(int id);
	public Book create(Book book);
	public Boolean delete(int id);
	
}
