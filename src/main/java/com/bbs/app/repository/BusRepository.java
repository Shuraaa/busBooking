package com.bbs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbs.app.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>, PagingAndSortingRepository<Bus, Integer> {
	
	List<Bus> findByDriverNameContaining(String name);

	
}
