package com.tamil.bookmyshow.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.CustomerDAO;
import com.tamil.bookmyshow.dao.SeatDAO;
import com.tamil.bookmyshow.dao.ShowDAO;
import com.tamil.bookmyshow.dao.TheatreDAO;
import com.tamil.bookmyshow.dao.TicketDAO;

import com.tamil.bookmyshow.dto.SeatType;
import com.tamil.bookmyshow.entity.CustomerEntity;
import com.tamil.bookmyshow.entity.Seat;
import com.tamil.bookmyshow.entity.Show;

import com.tamil.bookmyshow.entity.Ticket;
import com.tamil.bookmyshow.exception.CustomerNotFoundException;
import com.tamil.bookmyshow.exception.InvalidDetailException;
import com.tamil.bookmyshow.exception.PasswordException;
import com.tamil.bookmyshow.exception.SeatNotFoundException;
import com.tamil.bookmyshow.exception.ShowNotFoundException;
import com.tamil.bookmyshow.exception.TicketNotFoundException;

import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDAO dao;
	@Autowired 
	private ShowDAO showDao;
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private TheatreDAO theatreDao;
	@Autowired
	private SeatDAO seatDao;
	
	public ResponseEntity<ResponseStructure<List<Ticket>>>save(int showId,SeatType seatType,int numberOfSeats,String customerEmail,String password){
		if(numberOfSeats<=0)throw new InvalidDetailException("The number of Seats to be booked for seat type "+seatType+ "is less than 1");
		CustomerEntity customer=customerDao.find(customerEmail);
		if(customer==null)throw new CustomerNotFoundException("Customer with email :"+customerEmail+" is not found");
		if(!customer.getPassword().equals(password))throw new PasswordException("Incorrect password for customer with email :"+customerEmail);
		
        Show show=showDao.find(showId);
        if(show==null)throw new ShowNotFoundException("Show not found with the given id:"+showId );                      
 		List<Seat>seats=new ArrayList<Seat>();
		for(Seat s:show.getSeats()) {
			if(s.getSeatType().equals(seatType))seats.add(s);
		}
		int totalSeats=theatreDao.find(show.getTheatreId()).getNumberOfSeats();
		int seatsForSilver=(totalSeats%2==1)?(totalSeats/2)+1:totalSeats/2;
		totalSeats=totalSeats/2;
		int seatsForGold=(totalSeats%2==1)?(totalSeats/2)+1:totalSeats/2;
		totalSeats=totalSeats/2;
		int seatsForBalcony=totalSeats;
		
		if(seatType.equals(SeatType.SILVER)) {
			if(seats.size()+numberOfSeats>seatsForSilver) throw new SeatNotFoundException("No Seats Available To Book for seat type "+seatType);
		}
		else if(seatType.equals( SeatType.GOLD)) {
			if(seats.size()+numberOfSeats>seatsForGold) throw new SeatNotFoundException("No Seats Available To Book for seat type "+seatType);
		}
		else  {
			if(seats.size()+numberOfSeats>seatsForBalcony) throw new SeatNotFoundException("No Seats Available To Book for seat type "+seatType);
		}
		List<Ticket>tickets=new ArrayList<>();
		int seatNumberStart=seats.size()+1;
		for(int i=1;i<=numberOfSeats;i++) {
			Seat s=new Seat();
			s.setSeatNo(seatNumberStart++);
			s.setSeatType(seatType);
			Ticket t=new Ticket();
			if(seatType.equals(SeatType.BALCONY))t.setPrice(show.getSeatPriceForBalcony());
			else if(seatType.equals(SeatType.GOLD))t.setPrice(show.getSeatPriceForGold());
			else t.setPrice(show.getSeatPriceForSilver());
			t.setSeat(s);
			s.setTicket(t);
			show.addSeat(s);
			customer.addTicket(t);
			tickets.add(t);
			seatDao.save(s);
			
		}
		showDao.update(show, show.getId());
		customerDao.update(customer, customer.getId());
		ResponseStructure<List<Ticket>>str=new ResponseStructure<>();
		if(numberOfSeats>1)str.setMessage("Tickets saved successfully");
		else str.setMessage("Ticket saved successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(tickets);
		return new ResponseEntity<ResponseStructure<List<Ticket>>>(str,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>>find(int id){
		if(id<=0)throw new InvalidDetailException("Id value should be greater than 0");
		Ticket t=dao.find(id);
		if(t==null)throw new TicketNotFoundException("Ticket not found with the given id :"+id);
		ResponseStructure<Ticket>str=new ResponseStructure<>();
		str.setMessage("Ticket Fetched Successfully");
		str.setStatus(HttpStatus.FOUND.value());
		str.setObject(t);
		return new ResponseEntity<ResponseStructure<Ticket>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Ticket>>update(Ticket ticket,int id){
		if(ticket==null)throw new TicketNotFoundException("Ticket is null");
		if(id<=0)throw new InvalidDetailException("Id value should be greater than 0");
		Ticket t=dao.find(id);
		if(t==null)throw new TicketNotFoundException("Ticket not found with the given id :"+id);
		ticket.setId(t.getId());
		ResponseStructure<Ticket>str=new ResponseStructure<>();
		str.setMessage("Ticket Updated Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dao.save(ticket));
		return new ResponseEntity<ResponseStructure<Ticket>>(str,HttpStatus.OK);
	}

}
