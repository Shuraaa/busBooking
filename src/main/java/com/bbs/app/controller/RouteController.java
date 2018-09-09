package com.bbs.app.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.bbs.app.model.Route;
import com.bbs.app.repository.RouteRepository;
import com.bbs.app.service.RouteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class RouteController {

	@Autowired
	RouteService routeService;

	@Autowired
	RouteRepository r;

	@GetMapping(value = "/routes/page={page}")
	public Page<Route> findAll(@PathVariable("page") int page) {
		return routeService.findAll(page);
	}

	@GetMapping(value = "/routes/price={price}")
	public List<Route> findByPrice(@PathVariable("price") double price) {
		return routeService.findByPrice(price);
	}

	@GetMapping(value = "/routes/start={start}")
	public List<Route> findByStart(@PathVariable("start") String start) {
		return routeService.findByStart(start);
	}

	@GetMapping(value = "/routes/end={end}")
	public List<Route> findByPrice(@PathVariable("end") String end) {
		return routeService.findByEnd(end);
	}

	@GetMapping(value = "/routes/id={id}")
	public Optional<Route> findById(@PathVariable("id") int id) {
		return routeService.findById(id);
	}

	@DeleteMapping(value = "/routes/delete={id}")
	public ResponseEntity<Route> delete(@PathVariable("id") int id) {
		return routeService.delete(id);
	}

	@DeleteMapping(value = "/routes/delete")
	public ResponseEntity<Route> deleteAll() {
		return routeService.deleteAll();
	}

	@PostMapping(value = "/routes")
	public Route create(@Valid @RequestBody Route route) {
		return routeService.create(route);
	}

	@PutMapping(value = "/routes/{id}")
	public ResponseEntity<Route> update(@PathVariable("id") int id, @RequestBody Route route) {
		return routeService.update(id, route);
	}

	@GetMapping(value = "/routes/start={start}/end={end}/startDate={startDate}")
	public List<Route> findByStartAndEndAndStartTime(@PathVariable("start") String start,
			@PathVariable("end") String end, @PathVariable("startDate") Date startDate) {
		return routeService.findByStartAndEndAndStartTime(start, end, startDate);
	}

	@GetMapping(value = "/routes/date={date}")
	public List<Route> findByStartDate(@PathVariable("date") Date date) {
		return routeService.findByStartDate(date);
	}
}
