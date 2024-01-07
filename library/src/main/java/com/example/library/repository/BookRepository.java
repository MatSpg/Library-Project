package com.example.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>  {

}
