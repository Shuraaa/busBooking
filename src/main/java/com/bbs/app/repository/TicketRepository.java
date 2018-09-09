package com.bbs.app.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbs.app.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer>, JpaRepository<Ticket, Integer> {

	List<Ticket> findByBookingDate(Date date);

	@Query("select t from Ticket t where t.user.userId=?1")
	List<Ticket> findByUserId(int id);

	@Query("select t from Ticket t where t.seats.seatId=?1")
	List<Ticket> findBySeatId(int id);

	@Query("select t from Ticket t where t.buses.busId=?1")
	List<Ticket> findByBusId(int id);

	@Query("select t from Ticket t where t.routes.routeId=?1")
	List<Ticket> findByRouteId(int id);

	@Query("select t from Ticket t where t.routes.routeId=?1 and t.buses.busId=?2 and t.seats.seatId=?3")
	Optional<Ticket> checkTicketByRouteBusSeatNumber(int route, int bus, int seatId);

	@Query("select t from Ticket t where t.routes.routeId=?1 and t.buses.busId=?2")
	List<Ticket> getSeatBookedByRouteAndBus(int route, int bus);

	@Query("select t from Ticket t where t.routes.routeId=?1 and t.buses.busId=?2 and t.bookingStatus='BOOKED'")
	List<Ticket> getAllSeatBooked(int route, int bus);

	List<Ticket> findByBookingStatus(String status);

	@Query(value = "select t.ticketId, s.seatId, s.seatName, t.bookingStatus from Ticket t left join t.seats s where t.routes.routeId=?1 and t.buses.busId=?2")
	List<Object[]> findSeatByRouteAndBus(int r, int b);

}
