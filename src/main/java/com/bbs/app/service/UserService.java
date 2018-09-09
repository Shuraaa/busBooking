package com.bbs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.bbs.app.model.User;

public interface UserService {

	abstract User create(int roleId, User user);

	abstract ResponseEntity<User> checkUser(String userName, String password);

	abstract ResponseEntity<User> update(int id, User user);

	abstract ResponseEntity<String> delete(int id);

	abstract ResponseEntity<String> deleteAll();

	abstract Optional<User> findById(int id);

	abstract List<User> findByContainingName(String name);

	abstract List<User> findByEmail(String email);

	abstract Page<User> findAll(int page);
	
	abstract User findByUserName(String username);
}
