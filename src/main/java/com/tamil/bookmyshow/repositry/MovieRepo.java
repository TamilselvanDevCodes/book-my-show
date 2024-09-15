package com.tamil.bookmyshow.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.Movie;







public interface MovieRepo extends JpaRepository<Movie,Integer>{
	 Optional<Movie> findByName(String name);
}
