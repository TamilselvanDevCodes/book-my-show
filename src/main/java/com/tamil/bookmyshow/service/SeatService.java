package com.tamil.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.SeatDAO;
import com.tamil.bookmyshow.entity.Seat;
import com.tamil.bookmyshow.exception.InvalidDetailException;
import com.tamil.bookmyshow.exception.SeatNotFoundException;

import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class SeatService {
		
	@Autowired
	private SeatDAO dao;
	
	public ResponseEntity<ResponseStructure<Seat>>save(Seat Seat){
		if(Seat==null)throw new SeatNotFoundException("Seat is null");
		ResponseStructure<Seat>str=new ResponseStructure<>();
		str.setMessage("Seat Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(dao.save(Seat));
		return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Seat>>find(int id){
		
		Seat t=dao.find(id);
		if(t==null)throw new SeatNotFoundException("Seat not found with the given id :"+id);
		ResponseStructure<Seat>str=new ResponseStructure<>();
		str.setMessage("Seat Fetched Successfully");
		str.setStatus(HttpStatus.FOUND.value());
		str.setObject(t);
		return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Seat>>update(Seat Seat,int id){
		if(Seat==null)throw new SeatNotFoundException("Seat is null");
	
		Seat t=dao.find(id);
		if(t==null)throw new SeatNotFoundException("Seat not found with the given id :"+id);
		Seat.setId(t.getId());
		ResponseStructure<Seat>str=new ResponseStructure<>();
		str.setMessage("Seat Updated Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dao.save(Seat));
		return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.OK);
	}
}
