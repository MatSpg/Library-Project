package com.example.library.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.library.entity.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	@Qualifier("dbBookService")
	private BookService bookService;
	
	@PostMapping("/new")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookService.create(book);
	}
	
	@GetMapping("/all")
	public Iterable<Book> getAllBook() {
		return bookService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Book> getBookById(@PathVariable int id) {
		return bookService.getById(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public Boolean deleteBook(@PathVariable int id) {
		Boolean foundBook = bookService.delete(id);
		
		if (foundBook == false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato.");
		}
		
		return foundBook;
	}
}
