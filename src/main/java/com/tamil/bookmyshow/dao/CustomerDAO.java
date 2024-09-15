package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.CustomerEntity;
import com.tamil.bookmyshow.repositry.CustomerRepo;

@Repository
public class CustomerDAO {
	
	@Autowired
	private CustomerRepo repo;
	
	public CustomerEntity save(CustomerEntity entity) {
		return repo.save(entity);
	}
	public CustomerEntity find(int id) {
		Optional<CustomerEntity>opt =repo.findById(id);
		if(opt.isPresent())return opt.get();
		return null;
	}
	public CustomerEntity update(CustomerEntity entity,int id) {
		CustomerEntity e=find(id);
		if(e==null||entity==null)return null;
		entity.setId(e.getId());
		entity.setTickets(e.getTickets());
		return repo.save(entity);
	}
	public CustomerEntity delete(int id) {
		CustomerEntity e=find(id);
		if(e==null) return null;
		 repo.deleteById(id);
		return e;
			
		}
	public CustomerEntity find(String email) {
		Optional<CustomerEntity>opt =repo.findByEmail(email);
		if(opt.isPresent())return opt.get();
		return null;
	}
}
