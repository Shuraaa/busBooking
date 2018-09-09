package com.bbs.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "seats")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int seatId;

	@Column(name = "name")
	private String seatName;

	public Seat() {
	}

	public Seat(int seatId, String seatName) {
		super();
		this.seatId = seatId;
		this.seatName = seatName;
	}
}
