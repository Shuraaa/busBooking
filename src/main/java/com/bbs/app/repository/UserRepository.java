package com.bbs.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbs.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

	List<User> findByUserNameContaining(String userName);

	List<User> findByEmailContaining(String email);

}