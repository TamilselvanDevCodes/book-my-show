package com.tamil.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tamil.bookmyshow.dto.SeatType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message="seat number cannot be null")
	@Min(value=1,message = "seat number should be greater than 0")
	private int seatNo;
	@NotNull(message="seat Type cannot be null")
	private SeatType seatType;
	@NotNull(message="show id cannot be null")
	private int showId;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Ticket ticket;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType=seatType;
    }
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatType=" + seatType + "]";
	}
	
	
	
}
