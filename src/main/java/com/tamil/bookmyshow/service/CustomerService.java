package com.tamil.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.CustomerDAO;
import com.tamil.bookmyshow.dao.SeatDAO;
import com.tamil.bookmyshow.dao.ShowDAO;
import com.tamil.bookmyshow.dao.TicketDAO;
import com.tamil.bookmyshow.dto.CustomerDTO;
import com.tamil.bookmyshow.entity.CustomerEntity;
import com.tamil.bookmyshow.entity.Seat;
import com.tamil.bookmyshow.entity.Show;
import com.tamil.bookmyshow.entity.Ticket;
import com.tamil.bookmyshow.exception.CustomerNotFoundException;
import com.tamil.bookmyshow.exception.PasswordException;
import com.tamil.bookmyshow.exception.ShowNotFoundException;
import com.tamil.bookmyshow.exception.TicketNotFoundException;
import com.tamil.bookmyshow.util.Mapper;
import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private ShowDAO showDao;
	@Autowired 
	private SeatDAO seatDao;
	@Autowired
	private TicketDAO ticketDao;
	public ResponseEntity<ResponseStructure<CustomerDTO>> save(CustomerEntity entity){
		if(entity==null)throw new CustomerNotFoundException("Customer value is null");

		customerDAO.save(entity);
		CustomerDTO dto= Mapper.mapCustomer(entity);
		ResponseStructure<CustomerDTO>str=new ResponseStructure<>();
		str.setMessage("Customer Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<CustomerDTO>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<CustomerDTO>> find(int id){
	
		CustomerEntity e=customerDAO.find(id);
		if(e==null)throw new CustomerNotFoundException("Customer Not found with given Id :"+id);
		CustomerDTO dto= Mapper.mapCustomer(e);
		ResponseStructure<CustomerDTO>str=new ResponseStructure<>();
		str.setMessage("Customer Fetched Successfully");
		str.setStatus(HttpStatus.FOUND.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<CustomerDTO>>(str,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<CustomerDTO>> update(CustomerEntity entity,String customerEmail,String customerPassword){
		customerLogin(customerEmail, customerPassword);
		if(entity==null)throw new CustomerNotFoundException("Customer value is null");
		CustomerEntity c=customerDAO.find(customerEmail);
		if(c==null)throw new CustomerNotFoundException("Customer Not found with given email :"+customerEmail);

		entity.setId(c.getId());
		customerDAO.update(entity, c.getId());
		CustomerDTO dto= Mapper.mapCustomer(entity);
		ResponseStructure<CustomerDTO>str=new ResponseStructure<>();
		str.setMessage("Customer Updated Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<CustomerDTO>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<CustomerDTO>> cancelTicket(int ticketId,String customerEmail,String customerPassword){
		customerLogin(customerEmail, customerPassword);
		
		CustomerEntity c=customerDAO.find(customerEmail);
		if(c==null)throw new CustomerNotFoundException("Customer Not found with given email :"+customerEmail);
        boolean flag=false;
        for(Ticket t:c.getTickets()) {
        	if(t.getId()==ticketId) {
        		flag =true;
        		c.getTickets().remove(t);
        	}
        }
        if(!flag)throw new TicketNotFoundException("Ticket Not found with the given customer "+c.getEmail());
        ticketDao.delete(ticketId);
		CustomerDTO dto= Mapper.mapCustomer(c);
		ResponseStructure<CustomerDTO>str=new ResponseStructure<>();
		str.setMessage("Ticket cancelled  Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<CustomerDTO>>(str,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<CustomerDTO>> delete(String customerEmail,String customerPassword){
		customerLogin(customerEmail, customerPassword);
		CustomerEntity e=customerDAO.find(customerEmail);
		List<Ticket>tickets=e.getTickets();
		
		for(Ticket t:tickets) {
		int showId=t.getSeat().getShowId();
		Show s=	showDao.find(showId);
		if(s==null)throw new ShowNotFoundException("Show not found with the given Id "+showId);
		Seat seat=t.getSeat();
		s.removeSeat(seat);
		t.setSeat(null);
		seat.setTicket(null);
		seatDao.delete(t.getSeat().getId());
		showDao.update(s, showId);
		}
		customerDAO.delete(e.getId());
		CustomerDTO dto= Mapper.mapCustomer(e);
		ResponseStructure<CustomerDTO>str=new ResponseStructure<>();
		str.setMessage("Customer deleted Successfully");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<CustomerDTO>>(str,HttpStatus.OK);
	}

	public void customerLogin(String customerEmail,String customerPassword) {
		CustomerEntity c=customerDAO.find(customerEmail);
		if(c==null)throw new CustomerNotFoundException("Customer not found with the given email: "+customerEmail);
		if(!c.getPassword().equals(customerPassword))throw new PasswordException("Invalid Password for customer "+customerEmail);
	}
	
}
