package com.bbs.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbs.app.model.User;
import com.bbs.app.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users/page={page}")
	public Page<User> findAll(@PathVariable int page) {
		return userService.findAll(page);
	}

	@PostMapping(value = "/users/create/role={roleId}")
	public User create(@PathVariable(value = "roleId") int roleId, @Valid @RequestBody User user) {
		return userService.create(roleId, user);
	}

	@DeleteMapping(value = "/users/delete={id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		return userService.delete(id);
	}

	@DeleteMapping(value = "/users/delete")
	public ResponseEntity<String> deleteAll() {
		return userService.deleteAll();
	}

	@GetMapping(value = "/users/name={userName}")
	public List<User> findByContainingName(@PathVariable String userName) {
		return userService.findByContainingName(userName);
	}

	@GetMapping(value = "/users/email={email}")
	public List<User> findByEmail(@PathVariable("email") String email) {
		return userService.findByEmail(email);
	}

	@PutMapping(value = "/users/{id}")
	public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User user) {
		return userService.update(id, user);
	}

	@GetMapping(value = "/users/id={id}")
	public Optional<User> findById(@PathVariable("id") int id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/login/{userName}+{password}", method = RequestMethod.GET)
	public ResponseEntity<User> checkUser(@PathVariable("userName") String userName,
			@PathVariable("password") String password) {
		return userService.checkUser(userName, password);
	}

	@PostMapping("/login")
	public ResponseEntity<User> checkUser(@RequestBody User user) {
		return userService.checkUser(user.getUserName(), user.getPassword());
	}

	@GetMapping("/users/name/{userName}")
	public User findByUsername(@PathVariable String userName) {
		return userService.findByUserName(userName);
	}
}
