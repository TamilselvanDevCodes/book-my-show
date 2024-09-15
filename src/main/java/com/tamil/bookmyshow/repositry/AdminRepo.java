package com.tamil.bookmyshow.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, String>{
		Optional<AdminEntity>findByContactNumber(long contactNumber);
			
		
}
