package com.bbs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbs.app.model.Role;
import com.bbs.app.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("Select u from User u where u.role.roleId=?1")
	List<User> findUserByRole(int q);
	
}
