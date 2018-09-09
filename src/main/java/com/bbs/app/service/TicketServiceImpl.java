package com.bbs.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.bbs.app.model.Ticket;
import com.bbs.app.repository.BusRepository;
import com.bbs.app.repository.RouteRepository;
import com.bbs.app.repository.SeatRepository;
import com.bbs.app.repository.TicketRepository;
import com.bbs.app.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	RouteRepository routeRepository;

	@Override
	public Page<Ticket> findAll(int page) {
		Pageable pageable = PageRequest.of(page, 8);
		return ticketRepository.findAll(pageable);
	}

	@Override
	public List<Ticket> findByBookingDate(Date date) {
		return ticketRepository.findByBookingDate(date);
	}

	@Override
	public Optional<Ticket> findByTicketId(int id) {
		return ticketRepository.findById(id);
	}

	@Override
	public List<Ticket> findByUserId(int userId) {
		return ticketRepository.findByUserId(userId);
	}

	@Override
	public List<Ticket> findBySeatId(int seatId) {
		return ticketRepository.findBySeatId(seatId);
	}

	@Override
	public List<Ticket> findByBusId(int seatId) {
		return ticketRepository.findByBusId(seatId);
	}

	@Override
	public List<Ticket> findByRouteId(int routeId) {
		return ticketRepository.findByRouteId(routeId);
	}

	@Override
	public ResponseEntity<Ticket> delete(int id) {
		Optional<Ticket> tickData = ticketRepository.findById(id);

		if (tickData.isPresent()) {
			ticketRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Ticket " + id + " not found!..");
		}
	}

	@Override
	public ResponseEntity<Ticket> deleteAll() {
		ticketRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public Ticket create(int userId, int routeId, int busId, int seatId, Ticket ticket) {

		int ticketID = ticket.getTicketId();
		int busSize = busRepository.findById(busId).get().getNumberOfSeat();

		// Check if Ticket already exist
		if (!ticketRepository.existsById(ticketID)) {

			// Check for user ID then map to new Ticket
			return userRepository.findById(userId).map(user -> {
				ticket.setUser(user);

				// Check if new Route, Bus, Seat has been booked or not. Then map to new Ticket
				if (!ticketRepository.checkTicketByRouteBusSeatNumber(routeId, busId, seatId).isPresent()) {

					return routeRepository.findById(routeId).map(route -> {
						ticket.setRoutes(route);

						// Validate available seat on every Bus then start mapping
						if (getSeatBookedByRouteAndBus(routeId, busId).size() < busSize) {
							System.out.println("1: " + busSize);
							return busRepository.findById(busId).map(bus -> {
								ticket.setBuses(bus);

								return seatRepository.findById(seatId).map(seat -> {
									ticket.setSeats(seat);

									// Set status and Date for new Ticket
									ticket.setBookingStatus("NONE");
									ticket.setBookingDate(Date.valueOf(LocalDate.now()));
									return ticketRepository.save(ticket);

								}).orElseThrow(() -> new ResourceNotFoundException("Seat " + seatId + " not found!.."));
							}).orElseThrow(() -> new ResourceNotFoundException("Bus " + busId + " not found!.."));
						} else {
							throw new ResourceNotFoundException("Route " + routeId + " , Bus " + busId + " full!..");
						}
					}).orElseThrow(() -> new ResourceNotFoundException("Route " + routeId + " not found!.."));

					// } else if ((!ticketRepository.checkTicketByRouteBusSeatNumber(routeId, busId,
					// seatId).isPresent())
					// && (!ticketRepository.checkTicketByRouteSeatNumber(routeId,
					// seatId).isPresent())) {
					//
					// return routeRepository.findById(routeId).map(route -> {
					// ticket.setRoutes(route);
					// if (checkSeatBooked(routeId, busId) < busSize) {
					// System.out.println("2: " + busSize);
					// return busRepository.findById(busId).map(bus -> {
					// ticket.setBuses(bus);
					//
					// return seatRepository.findById(seatId).map(seat -> {
					// ticket.setSeats(seat);
					// ticket.setBookingStatus("BOOKED");
					// return ticketRepository.save(ticket);
					//
					// }).orElseThrow(() -> new ResourceNotFoundException("Seat " + seatId + " not
					// found!.."));
					// }).orElseThrow(() -> new ResourceNotFoundException("Bus " + busId + " not
					// found!.."));
					// } else {
					// throw new ResourceNotFoundException("Route " + routeId + " , Bus " + busId +
					// " full!..");
					// }
					// }).orElseThrow(() -> new ResourceNotFoundException("Route " + routeId + " not
					// found!.."));
					//
					// } else if ((!ticketRepository.checkTicketByRouteBusSeatNumber(routeId, busId,
					// seatId).isPresent())
					// && (!ticketRepository.checkTicketByBusSeatNumber(busId, seatId).isPresent()))
					// {
					// return routeRepository.findById(routeId).map(route -> {
					// ticket.setRoutes(route);
					// if (checkSeatBooked(routeId, busId) < busSize) {
					// System.out.println("3: " + busSize);
					// return busRepository.findById(busId).map(bus -> {
					// ticket.setBuses(bus);
					//
					// return seatRepository.findById(seatId).map(seat -> {
					// ticket.setSeats(seat);
					// ticket.setBookingStatus("BOOKED");
					// return ticketRepository.save(ticket);
					//
					// }).orElseThrow(() -> new ResourceNotFoundException("Seat " + seatId + " not
					// found!.."));
					// }).orElseThrow(() -> new ResourceNotFoundException("Bus " + busId + " not
					// found!.."));
					// } else {
					// throw new ResourceNotFoundException("Route " + routeId + " , Bus " + busId +
					// " full!..");
					// }
					// }).orElseThrow(() -> new ResourceNotFoundException("Route " + routeId + " not
					// found!.."));
				} else {
					throw new ResourceNotFoundException(
							"Seat: " + seatId + " on Bus: " + busId + ", Route: " + routeId + " has been booked!..");
				}

			}).orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found!.."));
		} else {
			throw new ResourceNotFoundException("Ticket with ID: " + ticketID + " already exist!..");
		}

		/* End create() Ticket method */
	}

	@Override
	public ResponseEntity<Ticket> update(int idTicket, Ticket ticket) {
		Optional<Ticket> tickData = ticketRepository.findById(idTicket);

		if (tickData.isPresent()) {
			Ticket tickPres = tickData.get();

			tickPres.setUser(ticket.getUser());
			tickPres.setSeats(ticket.getSeats());
			tickPres.setBuses(ticket.getBuses());
			tickPres.setRoutes(ticket.getRoutes());
			tickPres.setBookingDate(ticket.getBookingDate());
			tickPres.setBookingStatus(ticket.getBookingStatus().toUpperCase());

			return new ResponseEntity<>(ticketRepository.save(tickPres), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Ticket " + idTicket + " not found!..");
		}
	}

	@Override
	public List<Ticket> getSeatBookedByRouteAndBus(int routeId, int busId) {
		List<Ticket> list = ticketRepository.getSeatBookedByRouteAndBus(routeId, busId);
		return list;
	}

	@Override
	public ResponseEntity<Ticket> cancelTicketBooked(int ticketId) {
		Optional<Ticket> ticketData = ticketRepository.findById(ticketId);

		if (ticketData.isPresent()) {
			Ticket ticketPres = ticketData.get();
			ticketPres.setBookingStatus("CANCELED");
			return new ResponseEntity<Ticket>(ticketRepository.save(ticketPres), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Ticket " + ticketId + " not found!..");
	}

	@Override
	public List<Ticket> findByBookingStatus(String status) {
		return ticketRepository.findByBookingStatus(status);
	}

	@Override
	public int getAllAvailableSeat(int route, int bus) {
		int busSize = busRepository.findById(bus).get().getNumberOfSeat();
		int available = busSize - ticketRepository.getAllSeatBooked(route, bus).size();
		return available;
	}

	@Override
	public List<Object[]> getSeatByRouteAndBus(int route, int bus) {
		List<Object[]> list = new ArrayList<>();
		ticketRepository.findSeatByRouteAndBus(route, bus).forEach(list::add);
		return list;
	}


}
