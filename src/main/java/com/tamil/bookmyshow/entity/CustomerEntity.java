package com.tamil.bookmyshow.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name cannot be null")
	private String name;
	@Email(message = "email has invalid detail")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message = "PAssword cannot be null")
	private String password;
    @OneToMany(cascade = CascadeType.ALL)
	private List<Ticket>tickets;
	
	public CustomerEntity() {
		
		tickets=new ArrayList<>();
	}
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    public List<Ticket> getTickets() {
		return tickets;
	}
    public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	 
public boolean addTicket(Ticket ticket) {
	return tickets.add(ticket);
}
	
}
