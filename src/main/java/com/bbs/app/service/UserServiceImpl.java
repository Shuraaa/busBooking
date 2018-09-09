package com.bbs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bbs.app.exception.ResourceNotFoundException;
import com.bbs.app.model.User;
import com.bbs.app.repository.RoleRepository;
import com.bbs.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User create(int roleId, User user) {

		return roleRepository.findById(roleId).map(role -> {
			user.setRole(role);
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("Role " + roleId + " not found!.."));
	}

	@Override
	public ResponseEntity<User> update(int id, User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User userPres = userData.get();
			userPres.setUserName(user.getUserName());
			userPres.setFullName(user.getFullName());
			userPres.setPassword(user.getPassword());
			userPres.setEmail(user.getEmail());
			userPres.setRole(user.getRole());
			return new ResponseEntity<>(userRepository.save(userPres), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<String> delete(int id) {
		Optional<User> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			userRepository.deleteById(id);
			return new ResponseEntity<>("User deleted!..", HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<String> deleteAll() {
		userRepository.deleteAll();
		return new ResponseEntity<>("Deleted Users!..", HttpStatus.OK);
	}

	@Override
	public List<User> findByContainingName(String name) {
		List<User> user = userRepository.findByUserNameContaining(name);
		return user;
	}

	@Override
	public List<User> findByEmail(String email) {
		if (userRepository.findByEmailContaining(email).isEmpty()) {
			throw new ResourceNotFoundException("Email " + email + " not found!..");
		} else {
			return userRepository.findByEmailContaining(email);
		}
	}

	@Override
	public Page<User> findAll(int page) {
		Pageable pageable = PageRequest.of(page, 8);
		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<User> findById(int id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public ResponseEntity<User> checkUser(String userName, String password) {
		Optional<User> userData = userRepository.findByUserName(userName);

		if (userData.isPresent()) {
			User user = userData.get();
			if (user.getUserName().equals(userName) && user.getPassword().equals(password))
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Username: " + userName + " not found!..");
		}
	}

	@Override
	public User findByUserName(String userName) {
		Optional<User> users = userRepository.findByUserName(userName);

		if (users.isPresent())
			return users.get();
		return null;
	}

}
