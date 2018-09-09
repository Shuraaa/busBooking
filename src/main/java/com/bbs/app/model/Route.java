package com.bbs.app.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "routes")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int routeId;

	@Column(name = "route")
	private String route;

	@Column(name = "start_point")
	private String start;

	@Column(name = "end_point")
	private String end;

	@Column(name = "start_date")
	private Date startDate;

	// @JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@Column(name = "start_time")
	private Time startTime;

	@Column(name = "price")
	private double price;

	public Route() {
	}

	public Route(String route, String start, String end, Date startDate, Time startTime, double price) {
		super();
		this.route = route;
		this.start = start;
		this.end = end;
		this.startDate = startDate;
		this.startTime = startTime;
		this.price = price;
	}

	// @JsonDeserialize(using = CustomJsonDateDeserializer.class)
	// public void setStartTime(java.util.Date startTime) {
	// this.startTime = startTime;
	// }
	//
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// public Date getStartTime() {
	// return this.startTime;
	// }
}
