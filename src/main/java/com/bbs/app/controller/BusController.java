package com.bbs.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.bbs.app.model.Bus;
import com.bbs.app.service.BusService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class BusController {

	@Autowired
	BusService busService;

	@GetMapping(value = "/buses/page={page}")
	public Page<Bus> findAll(@PathVariable("page") int page) {
		return busService.findAll(page);
	}

	@GetMapping(value = "/buses/id/{id}")
	public Optional<Bus> findById(@PathVariable("id") int id) {
		return busService.findById(id);
	}

	@GetMapping(value = "/buses/name/{name}")
	public List<Bus> findByDriver(@PathVariable("name") String name) {
		return busService.findByDriver(name);
	}

	@PostMapping(value = "/buses/create")
	public Bus create(@RequestBody Bus bus) {
		return busService.create(bus);
	}

	@PutMapping(value = "/buses/{id}")
	public ResponseEntity<Bus> update(@PathVariable("id") int id, @RequestBody Bus bus) {
		return busService.update(id, bus);
	}

	@DeleteMapping(value = "/buses/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		return busService.delete(id);
	}

	@DeleteMapping(value = "/buses/delete")
	public ResponseEntity<String> deleteAll() {
		return busService.deleteAll();
	}
}
