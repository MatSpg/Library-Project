package com.example.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service("dbBookService")
public class DbBookService implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Iterable<Book> getAll() {
		return bookRepository.findAll();
	}
	
	@Override
	public Optional<Book> getById(int id) {
		return bookRepository.findById(id);
	}
	
	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public Boolean delete(int id) {
		Optional<Book> foundUser = bookRepository.findById(id);
		
		if (foundUser.isEmpty()) {
			return false;
		}
		
		bookRepository.delete(foundUser.get());
		return true;
	}
	
}
