package com.bbs.app.controller;

import java.sql.Date;
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

import com.bbs.app.model.Ticket;
import com.bbs.app.repository.TicketRepository;
import com.bbs.app.service.TicketService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	TicketRepository ticketre;

	// @GetMapping(value = "/test/{r}/{b}")
	// public List<Object[]> check(@PathVariable("r") int r, @PathVariable("b") int
	// b) {
	// List<Object[]> list = new ArrayList<>();
	// ticketre.findSeatByRouteAndBus(r, b).forEach(list::add);
	// return list;
	// }

	@GetMapping(value = "/tickets/page={page}")
	public Page<Ticket> findAll(@PathVariable int page) {
		return ticketService.findAll(page);
	}

	@GetMapping(value = "/ticket/date/{date}")
	public List<Ticket> findByBookingDate(@PathVariable("date") Date date) {
		return ticketService.findByBookingDate(date);
	}

	@GetMapping(value = "/ticket/uid/{id}")
	public List<Ticket> findByUserId(@PathVariable("id") int id) {
		return ticketService.findByUserId(id);
	}

	@GetMapping(value = "/ticket/seat/{id}")
	public List<Ticket> findBySeatId(@PathVariable("id") int id) {
		return ticketService.findBySeatId(id);
	}

	@GetMapping(value = "/ticket/bus/{id}")
	public List<Ticket> findByBusId(@PathVariable("id") int id) {
		return ticketService.findByBusId(id);
	}

	@GetMapping(value = "/ticket/route/{id}")
	public List<Ticket> findByRouteId(@PathVariable("id") int id) {
		return ticketService.findByRouteId(id);
	}

	@GetMapping(value = "/ticket/id/{id}")
	public Optional<Ticket> findTicket(@PathVariable("id") int id) {
		return ticketService.findByTicketId(id);
	}

	@DeleteMapping(value = "/ticket/{id}")
	public ResponseEntity<Ticket> delete(@PathVariable("id") int id) {
		return ticketService.delete(id);
	}

	@DeleteMapping(value = "/ticket/delete")
	public ResponseEntity<Ticket> deleteAll() {
		return ticketService.deleteAll();
	}

	@PostMapping(value = "/ticket/create/{userID}/{routeID}/{busID}/{seatId}")
	public Ticket create(@PathVariable("userID") int userID, @PathVariable("routeID") int routeID,
			@PathVariable("busID") int busID, @PathVariable("seatId") int seatId, @RequestBody Ticket tick) {
		return ticketService.create(userID, routeID, busID, seatId, tick);
	}

	@PutMapping(value = "/ticket/{id}")
	public ResponseEntity<Ticket> update(@PathVariable("id") int id, @RequestBody Ticket tick) {
		return ticketService.update(id, tick);
	}

	@PutMapping(value = "/ticket/cancel/{id}")
	public ResponseEntity<Ticket> cancel(@PathVariable("id") int id) {
		return ticketService.cancelTicketBooked(id);
	}

	@GetMapping(value = "/ticket/status/{status}")
	public List<Ticket> findByBookingStatus(@PathVariable("status") String status) {
		return ticketService.findByBookingStatus(status);
	}

	@GetMapping(value = "/ticket/available/{route}/{bus}")
	public String findAllAvailableSeat(@PathVariable("route") int route, @PathVariable("bus") int bus) {
		return ticketService.getAllAvailableSeat(route, bus) + " seats available.";
	}

	@GetMapping(value = "/ticket/find/{r}/{b}")
	public List<Object[]> check(@PathVariable("r") int r, @PathVariable("b") int b) {
		return ticketService.getSeatByRouteAndBus(r, b);
	}
}
