package com.deal.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deal.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String Email);
	
}
