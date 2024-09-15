package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.Ticket;
import com.tamil.bookmyshow.repositry.TicketRepo;

@Repository
public class TicketDAO {

	@Autowired
	private TicketRepo repo;
	
	public Ticket save(Ticket ticket) {
		return repo.save(ticket);
	}
	public Ticket find(int id) {
		Optional<Ticket>opt=repo.findById(id);
		if(opt.isPresent())return opt.get();
		return null;
	}
	public Ticket delete(int id) {
		Ticket t=find(id);
		if(t==null)return null;
		repo.deleteById(id);
		return t;
	}
}
