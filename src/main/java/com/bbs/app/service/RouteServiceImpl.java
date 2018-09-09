package com.bbs.app.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bbs.app.exception.ResourceNotFoundException;
import com.bbs.app.model.Route;
import com.bbs.app.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteRepository routeRepository;

	public Page<Route> findAll(int page) {
		Pageable pageable = PageRequest.of(page, 5);
		return routeRepository.findAll(pageable);
	}

	public Optional<Route> findById(int id) {
		return routeRepository.findById(id);
	}

	public List<Route> findByPrice(double price) {
		return routeRepository.findByPrice(price);
	}

	public List<Route> findByStart(String start) {
		return routeRepository.findByStartContaining(start);
	}

	public List<Route> findByEnd(String end) {
		return routeRepository.findByEndContaining(end);
	}

	public ResponseEntity<Route> delete(int id) {
		Optional<Route> routeData = routeRepository.findById(id);
		if (routeData.isPresent()) {
			routeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Route " + id + " not found!..");
		}
	}

	public ResponseEntity<Route> deleteAll() {
		routeRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<Route> update(int id, Route route) {
		Optional<Route> routeData = routeRepository.findById(id);
		if (routeData.isPresent()) {
			Route routePres = routeData.get();
			routePres.setRoute(route.getRoute());
			routePres.setStart(route.getStart());
			routePres.setEnd(route.getEnd());
			routePres.setStartDate(route.getStartDate());
			routePres.setStartTime(route.getStartTime());
			routePres.setPrice(route.getPrice());
			return new ResponseEntity<>(routeRepository.save(routePres), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Route " + id + " not found!..");
		}
	}

	public Route create(Route route) {
		return routeRepository.save(new Route(route.getRoute(), route.getStart(), route.getEnd(), route.getStartDate(),
				route.getStartTime(), route.getPrice()));
	}

	@Override
	public List<Route> findByStartAndEndAndStartTime(String start, String end, Date startDate) {
		return routeRepository.findByStartAndEndAndStartDateContaining(start, end, startDate);
	}

	@Override
	public List<Route> findByStartDate(Date date) {
		return routeRepository.findByStartDate(date);
	}

}
