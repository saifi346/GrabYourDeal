package com.deal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.exceptions.UserNotFoundException;
import com.deal.model.User;
import com.deal.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public List<User> getAllUsers() {
		List<User> users = repo.findAll();
		return users;
	}

	public User getUserByName(String username) throws UserNotFoundException {
		Optional<User> user = repo.findByUsername(username);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException("No record Found for the user : " + username);
		}
	}

	public void updateUser(String username, User user) throws UserNotFoundException {
		Optional<User> userRecord = repo.findByUsername(username);
		if (userRecord.isPresent()) {
			user.setId(userRecord.get().getId());
			user.setPassword(userRecord.get().getPassword());
			user.setRoles(userRecord.get().getRoles());
			repo.save(user);
		} else {
			throw new UserNotFoundException("No record Found for the user : " + user.getUsername());
		}
	}

	public void deleteUserByName(String username) throws UserNotFoundException {
		Optional<User> user = repo.findByUsername(username);
		if (user.isPresent()) {
			repo.delete(user.get());
		} else {
			throw new UserNotFoundException("No record Found for the user : " + username);
		}
	}
}
