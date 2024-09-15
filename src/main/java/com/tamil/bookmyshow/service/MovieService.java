package com.tamil.bookmyshow.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.tamil.bookmyshow.dao.ArtistDAO;
import com.tamil.bookmyshow.dao.MovieDAO;
import com.tamil.bookmyshow.entity.Artist;
import com.tamil.bookmyshow.entity.Movie;
import com.tamil.bookmyshow.exception.ArtistNotFoundException;
import com.tamil.bookmyshow.exception.InvalidDetailException;
import com.tamil.bookmyshow.exception.MovieNotFoundException;

import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class MovieService {
	@Autowired
	private MovieDAO dao;
	@Autowired
	private ArtistDAO artistDao;
	@Autowired
	private AdminService adminService;

	public ResponseEntity<ResponseStructure<Movie>> save(Movie movie,String adminEmail,String adminPassword){
		 adminService.adminLogin(adminEmail, adminPassword);
		if(movie==null)throw new MovieNotFoundException("The value of movie is null");

		ResponseStructure<Movie> str=new ResponseStructure<>();
		str.setMessage("Movie Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
	    str.setObject(dao.save(movie));
		return new ResponseEntity<>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Movie>> find(int id){
		
		Movie m=dao.find(id);
		if(m==null)throw new MovieNotFoundException("Movie not found with given id:"+id);
		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Fetched");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(m);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
		}
	public ResponseEntity<ResponseStructure<Movie>> find(String name){
		
		Movie m=dao.find(name);
		if(m==null)throw new MovieNotFoundException("Movie not found with given name:"+name);
		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Fetched");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(m);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Movie>> update(Movie movie,int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(movie==null)throw new MovieNotFoundException("The value of movie is null");
		if(dao.update(movie, id)==null)throw new MovieNotFoundException("Movie not found with given id:"+id);

		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(movie);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Movie>> update(Movie movie,String name,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(movie==null)throw new MovieNotFoundException("The value of movie is null");
		if(dao.update(movie, name)==null)throw new MovieNotFoundException("Movie not found with given name :"+name);

		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(movie);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Movie>> addArtist(String movieName,String artistName,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
	Movie m=dao.find(movieName);
	if(m==null)throw new MovieNotFoundException("Movie not found with given name: "+movieName);
	Artist a=artistDao.find(artistName);
	if(a==null)throw new ArtistNotFoundException("Artist not found with given name: "+artistName);
	m.addArtist(a);
	a.addMovie(m);
	dao.update(m, m.getId());
	ResponseStructure<Movie>str=new ResponseStructure<>();
	str.setMessage("Artist Successfully added");
	str.setStatus(HttpStatus.OK.value());
	str.setObject(m);
	return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Movie>> removeArtist(String movieName,String artistName,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
	Movie m=dao.find(movieName);
	if(m==null)throw new MovieNotFoundException("Movie not found with given name: "+movieName);
	Artist a=artistDao.find(artistName);
	if(a==null)throw new ArtistNotFoundException("Artist not found with given name: "+artistName);
	boolean flag2=a.removeMovie(m);
	if(!flag2)throw new InvalidDetailException("Movie is not present in the list to Remove");
	boolean flag1=m.removeArtist(a);
	if(!flag1)throw new InvalidDetailException("Artist is not present in the list to Remove");
	
	dao.update(m, m.getId());
	ResponseStructure<Movie>str=new ResponseStructure<>();
	str.setMessage("Artist Successfully removed");
	str.setStatus(HttpStatus.OK.value());
	str.setObject(m);
	return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> delete(int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Movie m=dao.find(id);
		if(m==null)throw new MovieNotFoundException("Movie not found with given id:"+id+" to delete");
		m.breakRelation();
		dao.update(m, m.getId());
		dao.delete(id);
		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(m);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
		
	}
	public ResponseEntity<ResponseStructure<Movie>> delete(String movieName,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Movie m=dao.find(movieName);
		if(m==null)throw new MovieNotFoundException("Movie not found with given name : "+movieName+" to delete");
		m.breakRelation();
		dao.update(m, m.getId());
		dao.delete(movieName);
		ResponseStructure<Movie>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(m);
		return new ResponseEntity<ResponseStructure<Movie>>(str,HttpStatus.OK);
}
	

}
