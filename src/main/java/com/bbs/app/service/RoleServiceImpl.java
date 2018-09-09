package com.bbs.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bbs.app.exception.ResourceNotFoundException;
import com.bbs.app.model.Role;
import com.bbs.app.model.User;
import com.bbs.app.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<User> findUserByRole(int q) {
		List<User> list = new ArrayList<>();
		roleRepository.findUserByRole(q).forEach(list::add);
		return list;
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role createRole(Role role) {
		Role _role = roleRepository.save(new Role(role.getRoleId(), role.getRoleName().toUpperCase()));
		return _role;
	}

	@Override
	public ResponseEntity<String> deleteRole(int id) {
		Optional<Role> roleData = roleRepository.findById(id);

		if (roleData.isPresent()) {
			roleRepository.deleteById(id);
			return new ResponseEntity<>("Role Deleted!..", HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Role " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<Role> updateRole(int id, Role role) {
		Optional<Role> roleData = roleRepository.findById(id);

		if (roleData.isPresent()) {
			Role rolePres = roleData.get();
			rolePres.setRoleId(role.getRoleId());
			rolePres.setRoleName(role.getRoleName().toUpperCase());
			return new ResponseEntity<>(roleRepository.save(rolePres), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Role " + id + " not found!..");
		}
	}

	@Override
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id);
	}
}
