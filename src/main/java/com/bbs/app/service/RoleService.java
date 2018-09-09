package com.bbs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bbs.app.model.Role;
import com.bbs.app.model.User;

public interface RoleService {

	abstract List<User> findUserByRole(int q);

	abstract List<Role> findAll();

	abstract Role createRole(Role role);

	abstract ResponseEntity<String> deleteRole(int id);

	abstract ResponseEntity<Role> updateRole(int id, Role role);

	abstract Optional<Role> findById(int id);
}
