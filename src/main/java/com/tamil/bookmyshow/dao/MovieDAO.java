package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.Movie;
import com.tamil.bookmyshow.repositry.MovieRepo;

@Repository
public class MovieDAO {
	@Autowired
	private MovieRepo repo;
	public Movie save(Movie movie) {
		return repo.save(movie);
	}
	public Movie find(int id) {
		Optional<Movie> opt=repo.findById(id);
		if(!opt.isPresent())return null;
		return opt.get();
	}
	public Movie find(String name) {
		Optional<Movie> opt=repo.findByName(name);
		if(!opt.isPresent())return null;
		return opt.get();
	}
     public Movie update(Movie movie, int id) {
	 Movie m=find(id);
    	 if(m==null)return null;
	  movie.setId(id);
	 movie.setArtists(m.getArtists());
	 return repo.save(movie);
}
     public Movie update(Movie movie,String name) {
    	 Movie m=find(name);
        	 if(m==null)return null;
    	  movie.setId(m.getId());
    	 movie.setArtists(m.getArtists());
    	 return repo.save(movie);
    }
   public Movie delete(int id) {
	   Movie m=find(id);
	   if(m==null)return null;
	   repo.deleteById(id);
	   return m; 
   }
   public Movie delete(String name) {
	   Movie m=find(name);
	   if(m==null)return null;
	   repo.delete(m);
	   return m;
   }
}
