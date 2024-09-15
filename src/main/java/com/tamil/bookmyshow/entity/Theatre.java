package com.tamil.bookmyshow.entity;

import java.util.List;


import com.tamil.bookmyshow.dto.City;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Theatre{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name cannot be null")
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Show>shows;
	@NotNull(message = "location cannot be null")
	private City location;
	@NotNull(message = "Seats can't be null")
	@Min(value = 200,message = "seats chould be greater than 199")
	@Max(value = 400,message = "seats should be less than 401")
	private int numberOfSeats;
	
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
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	public boolean addShow(Show show) {
		return shows.add(show);
	}
	public boolean removeShow(Show show) {
		return shows.remove(show);
	}
	public City getLocation() {
		return location;
	}
	public void setLocation(City location) {
		this.location = location;
	}
	


	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", shows=" + shows + ", location=" + location + "]";
	}

}
