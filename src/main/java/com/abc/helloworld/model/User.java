package com.abc.helloworld.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//Hibernate πÍ≈È§∆
@Entity
@Table(name = "employee")
public class User {

	private String account;
	private int userId;
	private String password;
	private String username;
	private int status;
	private Date create_time;

	public User() {
	}

	public User(String account, String password, String username) {
		this.setUsername(username);
		this.setAccount(account);
		this.setPassword(password);
	}

	@Column(name = "account")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Id
	@GeneratedValue
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "create_time")
	public String getCreate_time() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String createTime = df.format(create_time);
		return createTime;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
