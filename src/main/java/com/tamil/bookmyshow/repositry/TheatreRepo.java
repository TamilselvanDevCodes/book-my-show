package com.tamil.bookmyshow.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>{
	
}
