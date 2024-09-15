package com.tamil.bookmyshow.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Artist {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name cannot be null")
    private String name;
   
    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Movie>movies;
 
	public Artist() {
		movies=new ArrayList<>();
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
	


	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public void addMovie(Movie movie) {
		movies.add(movie);

	}
	public boolean removeMovie(Movie movie) {
		return movies.remove(movie);

}
	public boolean breakRelation() {
		if(movies.size()==0)return false;
		for(Movie m:movies) {
			m.getArtists().remove(this);
		}
		return true;
	}
	public void showMovies() {
		for(Movie movie:movies) {
			System.out.println(movie.getName()+" "+movie.getRatings());
		}
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + "]";
	}


    
}
