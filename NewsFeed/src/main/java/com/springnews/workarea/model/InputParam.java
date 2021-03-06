package com.springnews.workarea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="administrator")
public class InputParam{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "login should not be empty")
	@Size(min = 2, max = 10, message = "login should be between 2 and 10 characters")
	@Column(name="login")
	private String login;
	
	@NotEmpty(message = "password should not be empty")
	@Size(min = 2, max = 20, message = "password should be between 2 and 20 characters")
	@Column(name="password")
	private String password;
	
	public InputParam() {
	}
	
	public InputParam(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InputParam [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	

}
