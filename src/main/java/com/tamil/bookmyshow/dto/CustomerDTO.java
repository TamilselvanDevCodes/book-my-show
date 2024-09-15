package com.tamil.bookmyshow.dto;

import java.util.ArrayList;
import java.util.List;


import com.tamil.bookmyshow.entity.Ticket;

public class CustomerDTO {
	private int id;
	private String name;
	private String email;
	private List<Ticket>tickets;
	
	public CustomerDTO() {
		tickets=new ArrayList<>();
	}
	
	
	public List<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
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


	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	


	
}
