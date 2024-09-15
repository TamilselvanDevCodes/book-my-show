package com.tamil.bookmyshow.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;




@Entity

public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name cannot be null")
	private String name;
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Artist> artists;

	@NotNull(message = "Date cannot be null")
	
	private LocalDate dateOfRelease;
	private int ratings;
	

	public Movie() {
		artists=new ArrayList<>();
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

	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public LocalDate getDateOfRelease() {
		return dateOfRelease;
	}
	public void setDateOfRelease(LocalDate dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
	public int getRatings() {
		return ratings;
	}
	
	public void setRatings(int ratings) {
		if(ratings>10)ratings=10;
		if(ratings<=0)ratings=1;
		this.ratings += ratings;
		this.ratings=this.ratings/2;
	}
	public void addArtist(Artist artist) {
		artists.add(artist);
	
	}
	public boolean breakRelation() {
		if(artists.size()==0)return false;
		for(Artist a:artists) {
			a.getMovies().remove(this);
		}
		return true;
	}
	public boolean removeArtist(Artist artist) {
		return artists.remove(artist);
	
		
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", artists=" + artists + ", dateOfRelease=" + dateOfRelease
				+ ", ratings=" + ratings + "]";
	}



}
