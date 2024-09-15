package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.Artist;
import com.tamil.bookmyshow.entity.Movie;
import com.tamil.bookmyshow.repositry.ArtistRepo;

@Repository
public class ArtistDAO {
@Autowired
private ArtistRepo repo;

   public Artist save(Artist artist) {
	return repo.save(artist);
   }
   
   public Artist find(int id) {
	   Optional<Artist>optional=repo.findById(id);
	   if(optional.isPresent())return optional.get();
	   return null;
   }
   
   public Artist find(String name) {
	   Optional<Artist>optional=repo.findByName(name);
	   if(optional.isPresent())return optional.get();
	   return null;
   }
   public boolean deleteMovie(Movie movie) {
	   if(movie==null)return false;
	  for(Artist artist:movie.getArtists()) {
		 artist.removeMovie(movie);
		 repo.save(artist);
	  }
	  return true;
}
   public Artist update(Artist artist,int id) {
	   Artist a=find(id);
	   if(a==null||artist==null)return null;
	   artist.setMovies(a.getMovies());
	   artist.setId(id);
	  return repo. save(artist);
   }
   public Artist update(Artist artist,String name) {
	   Artist a=find(name);
	   if(a==null||artist==null)return null;
	   artist.setMovies(a.getMovies());
	   artist.setId(a.getId());
	  return repo. save(artist);
   }
   public Artist delete(int id) {
	   Artist a=find(id);
	   if(a==null)return null;
	   repo.deleteById(id);
	   return a;
   }
   public Artist delete(String name) {
	   Artist a=find(name);
	   if(a==null)return null;
	   repo.deleteById(a.getId());
	   return a;
   }
   
}
