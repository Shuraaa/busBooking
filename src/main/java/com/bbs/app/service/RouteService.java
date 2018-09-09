package com.bbs.app.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.bbs.app.model.Route;

public interface RouteService {

	abstract Page<Route> findAll(int page);

	abstract List<Route> findByPrice(double price);

	abstract Optional<Route> findById(int id);

	abstract List<Route> findByStart(String start);

	abstract List<Route> findByEnd(String end);

	abstract ResponseEntity<Route> delete(int id);

	abstract ResponseEntity<Route> deleteAll();

	abstract ResponseEntity<Route> update(int id, Route route);

	abstract Route create(Route route);

	abstract List<Route> findByStartAndEndAndStartTime(String start, String end, Date startDate);
	
	abstract List<Route> findByStartDate(Date time);
}
