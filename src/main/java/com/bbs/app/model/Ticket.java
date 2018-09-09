package com.bbs.app.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int ticketId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seats;

	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus buses;

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route routes;

	@Column(name = "booking_date")
	private Date bookingDate;

	@Column(name = "booking_status")
	private String bookingStatus;

	public Ticket() {
	}

	public Ticket(int ticketId, Date bookingDate, String bookingStatus) {
		super();
		this.ticketId = ticketId;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
	}

	public Ticket(User user, Seat seats, Bus buses, Route routes, Date bookingDate, String bookingStatus) {
		super();
		this.user = user;
		this.seats = seats;
		this.buses = buses;
		this.routes = routes;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
	}
}
