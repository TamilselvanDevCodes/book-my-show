package com.tamil.bookmyshow.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{
	Optional<CustomerEntity> findByEmail (String email);
	
}
