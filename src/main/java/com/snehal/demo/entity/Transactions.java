package com.snehal.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Transactions {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="acno")
	@JsonIgnore
	private User user;
	@Column(columnDefinition = "bigint default 0")
	private long racno;
	private TransactionType trtype;
	private double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	public long getRacno() {
		return racno;
	}
	public void setRacno(long racno) {
		this.racno = racno;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public TransactionType getTrtype() {
		return trtype;
	}
	public void setTrtype(TransactionType trtype) {
		this.trtype = trtype;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", user=" + user + ", racno=" + racno + ", Trtype=" + trtype + ", amount="
				+ amount + ", time=" + time + "]";
	}
	
	

}
