package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.dto.SeatType;
import com.tamil.bookmyshow.entity.Seat;
import com.tamil.bookmyshow.repositry.SeatRepo;
@Repository
public class SeatDAO {
	
	@Autowired
	private SeatRepo repo;
	
	public Seat save(Seat seat) {
		return repo.save(seat);
	}
	public Seat find(int id) {
		Optional<Seat>opt=repo.findById(id);
		if(opt.isPresent())return opt.get();
		return null;
	}
	public Seat delete(int id) {
		Seat t=find(id);
		if(t==null)return null;
		repo.deleteById(id);
		return t;
	}
	public Seat find(SeatType seatType) {
		Optional<Seat>opt=repo.findBySeatType(seatType);
		if(opt.isPresent())return opt.get();
		return null;
	}
}
