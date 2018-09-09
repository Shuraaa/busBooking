package com.bbs.app.service;

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
import com.bbs.app.model.Bus;
import com.bbs.app.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	BusRepository busRepository;

	@Override
	public Page<Bus> findAll(int page) {
		Pageable pageable = PageRequest.of(page, 6);
		return busRepository.findAll(pageable);
	}

	@Override
	public List<Bus> findByDriver(String driver) {
		return busRepository.findByDriverNameContaining(driver);
	}

	@Override
	public Optional<Bus> findById(int id) {
		return busRepository.findById(id);
	}

	@Override
	public Bus create(Bus bus) {
		return busRepository.save(new Bus(bus.getDriverName(), bus.getLicense(), bus.getNumberOfSeat()));
	}

	@Override
	public ResponseEntity<Bus> update(int id, Bus bus) {
		Optional<Bus> busData = busRepository.findById(id);

		if (busData.isPresent()) {
			Bus busPres = busData.get();
			busPres.setDriverName(bus.getDriverName());
			busPres.setNumberOfSeat(bus.getNumberOfSeat());
			busPres.setLicense(bus.getLicense());
			return new ResponseEntity<>(busRepository.save(busPres), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Bus " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<String> delete(int id) {
		Optional<Bus> busData = busRepository.findById(id);

		if (busData.isPresent()) {
			busRepository.deleteById(id);
			return new ResponseEntity<>("Bus " + id + " deleted!..", HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Bus " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<String> deleteAll() {
		busRepository.deleteAll();
		return new ResponseEntity<>("All bus data deleted!..", HttpStatus.OK);
	}
}
