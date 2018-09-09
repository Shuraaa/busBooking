package com.bbs.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int roleId;

	@Column(name = "role_name")
	private String roleName;

	public Role() {
	}

	public Role(int id, String name) {
		this.roleId = id;
		this.roleName = name;
	}

}
