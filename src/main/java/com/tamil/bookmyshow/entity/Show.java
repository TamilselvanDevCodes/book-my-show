package com.tamil.bookmyshow.entity;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="'show'")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Date cannot be null")
	private LocalDate dateOfStart;
	private LocalDate dateOfEnd;
	@NotNull(message = "Time cannot be null")
	private LocalTime timeOfStart=LocalTime.of(6, 0);
	private LocalTime timeOfEnd;
	@NotNull(message = "Theatre id for show can't be null")
	@Min(value = 1,message = "Theatre Id should be greater than 0")
	private int theatreId;
	@NotNull(message = "Movie id for show can't be null")
	@Min(value = 1,message = "Movie Id should be greater than 0")
	private int movieId;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seat>seats;
	
	private double  seatPriceForSilver=100;
    private double  seatPriceForGold=150;
	private double seatPriceForBalcony=200;

	
	
     public Show() {
	       seats=new ArrayList<>();
      }
     public LocalTime getTimeOfStart() {
 		return timeOfStart;
 	}
     public LocalTime getTimeOfEnd() {
 		return timeOfEnd;
 	}

    public LocalDate getDateOfStart() {
		return dateOfStart;
	}
    public LocalDate getDateOfEnd() {
 		return dateOfEnd;
 	}
    public void setDateOfStart(LocalDate dateOfStart) {
		this.dateOfStart = dateOfStart;
		setDateOfEnd();
	}
 
   private void setDateOfEnd() {
		if(timeOfStart.getHour()>=21||(timeOfStart.getHour()==20&&timeOfStart.getMinute()>=30)) {
			this.dateOfEnd=this.dateOfStart.plusDays(1);
		}
		this.dateOfEnd=this.dateOfStart;
	}
     public void setTimeOfStart(LocalTime timeOfStart) {
		this.timeOfStart = timeOfStart;
		setTimeOfEnd();
		setDateOfEnd();
	}
	private void setTimeOfEnd() {
		this.timeOfEnd = timeOfStart.plusHours(3);
		this.timeOfEnd=timeOfEnd.plusMinutes(30);
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	public boolean addSeat(Seat seat) {
		return seats.add(seat);
	}
	public boolean removeSeat(Seat seat) {
		return seats.remove(seat);
	}
	
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public double getSeatPriceForBalcony() {
		return seatPriceForBalcony;
	}
	public void setSeatPriceForBalcony(double seatPriceForBalcony) {
		this.seatPriceForBalcony = seatPriceForBalcony;
	}
	public double getSeatPriceForGold() {
		return seatPriceForGold;
	}
	public void setSeatPriceForGold(double seatPriceForGold) {
		this.seatPriceForGold = seatPriceForGold;
	}
	public double getSeatPriceForSilver() {
		return seatPriceForSilver;
	}
	public void setSeatPriceForSilver(double seatPriceForSilver) {
		this.seatPriceForSilver = seatPriceForSilver;
	}
	public String timeAndDateDetail() {
		return "("+dateOfStart+"/"+timeOfStart+" - "+dateOfEnd+"/"+timeOfEnd+")";
	}
}
