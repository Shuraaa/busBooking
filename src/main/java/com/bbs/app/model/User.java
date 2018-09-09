package com.bbs.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;

	@Column(name = "uname")
	private String userName;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "upassword")
	private String password;

	@Column(name = "phone")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
	}

	public User(String userName, String fullName, String password, String phone, String email) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.phoneNumber = phone;
		this.email = email;
	}
}
