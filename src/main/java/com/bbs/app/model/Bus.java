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
@Table(name = "buses")
public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int busId;

	@Column(name = "driver")
	private String driverName;

	@Column(name = "license")
	private String license;

	@Column(name = "number_of_seat")
	private int numberOfSeat;

	public Bus() {
	}

	public Bus(String driverName, String license, int numberOfSeat) {
		super();
		this.driverName = driverName;
		this.license = license;
		this.numberOfSeat = numberOfSeat;
	}

}
