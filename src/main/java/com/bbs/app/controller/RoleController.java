package com.bbs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbs.app.model.Role;
import com.bbs.app.model.User;
import com.bbs.app.service.RoleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping(value = "/roles")
	public List<Role> findAll() {
		return roleService.findAll();
	}

	@GetMapping(value = "/roles/id/{id}")
	public Optional<Role> findById(@PathVariable("id") int id) {
		return roleService.findById(id);
	}

	@GetMapping(value = "/roles/list/{roleId}")
	public List<User> findByRole(@PathVariable("roleId") int role) {
		return roleService.findUserByRole(role);
	}

	@PostMapping(value = "/roles")
	public Role create(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@DeleteMapping(value = "/roles/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		return roleService.deleteRole(id);
	}

	@PutMapping(value = "/roles/{id}")
	public ResponseEntity<Role> update(@PathVariable("id") int id, @RequestBody Role role) {
		return roleService.updateRole(id, role);
	}
}
