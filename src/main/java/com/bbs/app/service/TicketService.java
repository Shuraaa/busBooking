package com.bbs.app.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.bbs.app.model.Ticket;

public interface TicketService {

	abstract Page<Ticket> findAll(int page);

	abstract List<Ticket> findByBookingDate(Date date);

	abstract Optional<Ticket> findByTicketId(int id);

	abstract List<Ticket> findByUserId(int userId);

	abstract List<Ticket> findBySeatId(int seatId);

	abstract List<Ticket> findByBusId(int seatId);

	abstract List<Ticket> findByRouteId(int routeId);

	abstract ResponseEntity<Ticket> delete(int id);

	abstract ResponseEntity<Ticket> deleteAll();

	abstract Ticket create(int userId, int routeId, int busId, int seatId, Ticket ticket);

	abstract ResponseEntity<Ticket> update(int id, Ticket tick);

	abstract List<Ticket> getSeatBookedByRouteAndBus(int routeId, int busId);
	
	abstract ResponseEntity<Ticket> cancelTicketBooked(int ticketId);
	
	abstract List<Ticket> findByBookingStatus(String status);
	
	abstract int getAllAvailableSeat(int route, int bus);
	
	abstract List<Object[]> getSeatByRouteAndBus(int route, int bus);
	
}
