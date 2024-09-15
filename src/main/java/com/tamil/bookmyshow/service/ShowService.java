package com.tamil.bookmyshow.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.MovieDAO;
import com.tamil.bookmyshow.dao.ShowDAO;
import com.tamil.bookmyshow.dao.TheatreDAO;
import com.tamil.bookmyshow.entity.Movie;
import com.tamil.bookmyshow.entity.Show;
import com.tamil.bookmyshow.entity.Theatre;
import com.tamil.bookmyshow.exception.InvalidDetailException;
import com.tamil.bookmyshow.exception.MovieNotFoundException;
import com.tamil.bookmyshow.exception.MultipleShowException;
import com.tamil.bookmyshow.exception.ShowNotFoundException;
import com.tamil.bookmyshow.exception.TheatreNotFoundException;
import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class ShowService {
	
	@Autowired
	private ShowDAO showDao;
	@Autowired
	private MovieDAO movieDao;
	@Autowired
	private TheatreDAO theatreDao;
	
	@Autowired
	private AdminService adminService;
	
	
	public ResponseEntity<ResponseStructure<Show>>save(Show show,String adminEmail,String adminPassword){
		
	adminService.adminLogin(adminEmail, adminPassword);
	Movie m=movieDao.find(show.getMovieId());
	Theatre t=theatreDao.find(show.getTheatreId());
	if(m==null)throw new MovieNotFoundException("Movie is not found with the given Id: "+show.getMovieId());
	if(t==null)throw new TheatreNotFoundException("Theatre is not found with the given Id: "+show.getTheatreId());
	
	 if(m.getDateOfRelease().isAfter(show.getDateOfStart()))throw new InvalidDetailException("Movie "+m.getName()+" release date is["+m.getDateOfRelease()+"] but the show is assigned previously ["+show.getDateOfStart()+"]");
	 List<Show>currentShows= showDao.find(show.getDateOfStart(), show.getTheatreId());
	 for(Show s: currentShows) {
		 if(isInBetween(s.getTimeOfStart(),s.getTimeOfEnd(), show.getTimeOfStart())) 
			  throw new MultipleShowException("Multiple shows found within same time [previous show "+s.timeAndDateDetail()+"] and[ currently adding show "+show.timeAndDateDetail()+"]");
	  }
	 t.addShow(show);
	 
	 showDao.save(show);
	 ResponseStructure<Show>str=new ResponseStructure<>();
	 str.setMessage("Show Saved Successfully");
	 str.setStatus(HttpStatus.CREATED.value());
	 str.setObject(show);
		return new ResponseEntity<ResponseStructure<Show>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Show>> find(int id){
		
		Show s=showDao.find(id);
		if(s==null)throw new ShowNotFoundException("Show not found with given id "+id);
		ResponseStructure<Show>str=new ResponseStructure<>();
		 str.setMessage("Show Fetched Successfully");
		 str.setStatus(HttpStatus.FOUND.value());
		 str.setObject(s);
		return new ResponseEntity<ResponseStructure<Show>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Show>> find(LocalDate date,LocalTime time,int theatreId){

		Show s=showDao.find(date, time,theatreId);
		if(s==null)throw new ShowNotFoundException("Show not found with given date["+date+"] time["+time+"] theatre id["+theatreId+"]");
		ResponseStructure<Show>str=new ResponseStructure<>();
		 str.setMessage("Show Fetched Successfully");
		 str.setStatus(HttpStatus.FOUND.value());
		 str.setObject(s);
		return new ResponseEntity<ResponseStructure<Show>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Show>> update(int showId,LocalDate newDateOfStart,LocalTime newTimeOfStart,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Show s=showDao.find(showId);
		if(s==null)throw new ShowNotFoundException("Show not found with given id"+showId);
		
		s.setDateOfStart(newDateOfStart);
		s.setTimeOfStart(newTimeOfStart);
		List<Show>shows= showDao.find(newDateOfStart, s.getTheatreId());
		for(Show show:shows) {
			if(isInBetween(show.getTimeOfStart(),show.getTimeOfEnd() , newTimeOfStart))throw new InvalidDetailException("Multiple shows found within ["+show.getTimeOfStart()+" to "+show.getTimeOfEnd());
		}
		showDao.update(s, showId);
		ResponseStructure<Show>str=new ResponseStructure<>();
		 str.setMessage("Show updated Successfully");
		 str.setStatus(HttpStatus.OK.value());
		 str.setObject(s);
		return new ResponseEntity<ResponseStructure<Show>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Show>> delete(int showId,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		Show s=showDao.find(showId);
		
		if(s==null)throw new ShowNotFoundException("Show not found with given id"+showId);
		Theatre t=theatreDao.find(s.getTheatreId());
		t.removeShow(s);
		theatreDao.update(t, t.getId());
		showDao.delete(showId);
		ResponseStructure<Show>str=new ResponseStructure<>();
		 str.setMessage("Show deleted Successfully");
		 str.setStatus(HttpStatus.OK.value());
		 str.setObject(s);
		return new ResponseEntity<ResponseStructure<Show>>(str,HttpStatus.OK);
	}
	  private static boolean isInBetween(LocalTime timeOfStart, LocalTime timeOfEnd, LocalTime time) {
	        return (time.isAfter(timeOfStart) || time.equals(timeOfStart)) && time.isBefore(timeOfEnd);
	    }

	
}
