package com.bbs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.app.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	
}
