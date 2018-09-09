package com.bbs.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.app.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;

}
