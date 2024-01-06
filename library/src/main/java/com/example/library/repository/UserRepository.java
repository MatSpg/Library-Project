package com.example.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
