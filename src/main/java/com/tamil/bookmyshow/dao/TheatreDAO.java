package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.Theatre;
import com.tamil.bookmyshow.repositry.TheatreRepo;

@Repository
public class TheatreDAO {

	@Autowired
	private TheatreRepo repo;
	
	public Theatre save(Theatre Theatre) {
		return repo.save(Theatre);
	}
	public Theatre find(int id) {
		Optional<Theatre>opt=repo.findById(id);
		if(opt.isPresent())return opt.get();
		return null;
	}
	public Theatre delete(int id) {
		Theatre t=find(id);
		if(t==null)return null;
		repo.deleteById(id);
		return t;
	}
	public Theatre update(Theatre theatre,int id) {
		Theatre t=find(id);
		if(t==null)return null;
		theatre.setId(t.getId());
		theatre.setShows(t.getShows());
		return repo.save(theatre);
	}
}
