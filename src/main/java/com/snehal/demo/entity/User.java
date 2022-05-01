package com.snehal.demo.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.SequenceGenerators;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(name="seq", initialValue=100000000)
public class User {

	
	@Length(min=3, message="Name cannot be less than three characters")
	private String name;
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	@Column(unique = true)
	@Id
    private long acno;
	@Length(min=10,max=13,message="cell number must be of 10 characters")
	private String cellno;
	@Email
	@Column(unique = true)
	private String email;
	@Length(min=3,message="Address cannot be less than charachters")
    private String address;
	@Column(unique = true)
	@Length(min=10, max=10, message= "Pan no must be 10 characters")
    private String pan;
	@Length(min=12, max=12, message= "Aadhar no must be 12 character")
	@Column(unique = true)
    private String  adhaar;
    @Column(columnDefinition ="double default 10000.00" )
    private double balance = 10000;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(   cascade = CascadeType.ALL)
    @JoinColumn(name="acno")
	private List<Transactions> transactions = new ArrayList<>();
    //getter setter
    //toString
   
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAcno() {
		return acno;
	}
	public void setAcno(long acno) {
		this.acno = acno;
	}
	public String getCellno() {
		return cellno;
	}
	public void setCellno(String cellno) {
		this.cellno = cellno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAdhaar() {
		return adhaar;
	}
	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Transactions> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
	@Override
	public String toString() {
		return "User [ name=" + name + ", acno=" + acno + ", cellno=" + cellno + ", email=" + email
				+ ", address=" + address + ", pan=" + pan + ", adhaar=" + adhaar + ", balance=" + balance
				+ ", username=" + username + ", password=" + password + "]";
	}
    
	
	
	

}
