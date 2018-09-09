package com.bbs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.bbs.app.model.Bus;

public interface BusService {

	abstract Page<Bus> findAll(int page);

	abstract List<Bus> findByDriver(String driver);

	abstract Optional<Bus> findById(int id);

	abstract Bus create(Bus bus);

	abstract ResponseEntity<Bus> update(int id, Bus bus);

	abstract ResponseEntity<String> delete(int id);

	abstract ResponseEntity<String> deleteAll();
}
