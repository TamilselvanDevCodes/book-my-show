package com.tamil.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.CustomerDAO;
import com.tamil.bookmyshow.dao.ShowDAO;
import com.tamil.bookmyshow.dao.TheatreDAO;
import com.tamil.bookmyshow.entity.CustomerEntity;
import com.tamil.bookmyshow.entity.Seat;
import com.tamil.bookmyshow.entity.Show;
import com.tamil.bookmyshow.entity.Theatre;
import com.tamil.bookmyshow.exception.ShowNotFoundException;
import com.tamil.bookmyshow.exception.TheatreNotFoundException;

import com.tamil.bookmyshow.util.ResponseStructure;

@Service

public class TheatreService {

	@Autowired
	private TheatreDAO dao;
	
	@Autowired
	private ShowDAO showDao;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CustomerDAO customerDao;
	
	public ResponseEntity<ResponseStructure<Theatre>> save(Theatre theatre,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(theatre==null)throw new TheatreNotFoundException("Theatre is null");
	
		ResponseStructure<Theatre>str=new ResponseStructure<>();
		str.setMessage("Theatre Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(dao.save(theatre));
		return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Theatre>> find(int id){
		
		Theatre t=dao.find(id);
		if(t==null)throw new TheatreNotFoundException("Theatre NOT found with the given Id: "+id);
		ResponseStructure<Theatre>str=new ResponseStructure<>();
		str.setMessage("Theatre Fetched Successfully");
		str.setStatus(HttpStatus.FOUND.value());
		str.setObject(dao.save(t));
		return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Theatre>> update(Theatre theatre,int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
		if(theatre==null)throw new TheatreNotFoundException("Theatre is null");
		
		if(dao.find(id)==null)throw new TheatreNotFoundException("Theatre NOT found with the given Id: "+id);
		dao.update(theatre, id);
		ResponseStructure<Theatre>str=new ResponseStructure<>();
		str.setMessage("Theatre Updated Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(theatre);
		return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.OK);
		
	}
	public ResponseEntity<ResponseStructure<Theatre>> delete(int id,String adminEmail,String adminPassword){
		adminService.adminLogin(adminEmail, adminPassword);
	
		Theatre theatre=dao.find(id);
		if(theatre==null)throw new TheatreNotFoundException("Theatre NOT found with the given Id: "+id);
		for(Show show:theatre.getShows()) {
			List<Seat>seats=show.getSeats();
			for(Seat seat:seats) {
				CustomerEntity cus= customerDao.find(seat.getTicket().getCustomerId());
				cus.getTickets().remove(seat.getTicket());
				customerDao.update(cus, cus.getId());
			}
		}
		
		dao.delete(id);
		ResponseStructure<Theatre>str=new ResponseStructure<>();
		str.setMessage("Theatre deleted Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(theatre);
		return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.OK);
		
	}
	

    
	
	
	
}
