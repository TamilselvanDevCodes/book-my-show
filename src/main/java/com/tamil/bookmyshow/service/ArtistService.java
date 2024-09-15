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
public class ArtistService {
	@Autowired
	private ArtistDAO dao;
	@Autowired
	private MovieDAO movieDao;
	@Autowired
	private AdminService adminService;
	public ResponseEntity<ResponseStructure<Artist>> save(Artist artist,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(artist==null)throw new ArtistNotFoundException("Artist or Artist name is null");
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(dao.save(artist));
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Artist>> find(int id){
	    Artist a=dao.find(id);
		if(a==null)throw new ArtistNotFoundException("Artist not found with given id:"+id);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Fetched");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(a);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> find(String name){
	
		Artist a=dao.find(name);
		if(a==null)throw new ArtistNotFoundException("Artist not found with given name:"+name);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Fetched");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(a);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> addMovie(String artistName,String movieName,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(movieName==null)throw new MovieNotFoundException("The value of movie name is null");
		if(artistName==null)throw new ArtistNotFoundException("The value of artist name is null");
		Artist a=dao.find(artistName);
		if(a==null)throw new ArtistNotFoundException("Artist not found with given name: "+artistName);
		Movie m=movieDao.find(movieName);
		if(m==null)throw new MovieNotFoundException("Movie not found with given name: "+movieName);
		m.addArtist(a);
		a.addMovie(m);
		dao.update(a, a.getId());
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Movie Successfully added");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(a);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> removeMovie(String artistName,String movieName,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
	if(movieName==null)throw new MovieNotFoundException("The value of movie name is null");
	if(artistName==null)throw new ArtistNotFoundException("The value of artist name is null");
	Artist a=dao.find(artistName);
	if(a==null)throw new ArtistNotFoundException("Artist not found with given name: "+artistName);
	Movie m=movieDao.find(movieName);
	if(m==null)throw new MovieNotFoundException("Movie not found with given name: "+movieName);
	boolean flag1=m.removeArtist(a);
	if(!flag1)throw new InvalidDetailException("Artist is not present in the list to Remove");
	boolean flag2=a.removeMovie(m);
	if(!flag2)throw new InvalidDetailException("Movie is not present in the list to Remove");
    dao.update(a, a.getId());
	ResponseStructure<Artist>str=new ResponseStructure<>();
	str.setMessage("Movie Successfully removed");
	str.setStatus(HttpStatus.OK.value());
	str.setObject(a);
	return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> update(Artist artist,int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(artist==null)throw new ArtistNotFoundException("The value of artist is null");
		if(dao.update(artist, id)==null)throw new ArtistNotFoundException("artist not found with given id:"+id);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(artist);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> update(Artist artist,String name,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(artist==null)throw new ArtistNotFoundException("The value of artist is null");
	    if(dao.update(artist, name)==null)throw new ArtistNotFoundException("Artist not found with given name :"+name);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(artist);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Artist>> delete(int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Artist a=dao.find(id);
		if(a==null)throw new ArtistNotFoundException("Artist not found with given id:"+id+" to delete");
		a.breakRelation();
		dao.update(a,a.getId());
		dao.delete(id);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(a);
	    return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
	
	}
	public ResponseEntity<ResponseStructure<Artist>> delete(String name,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Artist a=dao.find(name);
		if(a==null)throw new ArtistNotFoundException("Artist not found with given name : "+name+" to delete");
		a.breakRelation();
		dao.update(a,a.getId());
		dao.delete(name);
		ResponseStructure<Artist>str=new ResponseStructure<>();
		str.setMessage("Artist Successfully Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(a);
		return new ResponseEntity<ResponseStructure<Artist>>(str,HttpStatus.OK);
		
	}
}
