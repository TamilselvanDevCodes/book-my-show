package com.tamil.bookmyshow.repositry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.Show;

public interface ShowRepo extends JpaRepository<Show, Integer>{
	Optional<Show> findByDateOfStartAndTimeOfStartAndTheatreId(LocalDate dateOfStart, LocalTime timeOfStart, int theatreId);
	
	 List<Optional<Show>> findByDateOfStartAndTheatreId(LocalDate dateOfStart,int theatreId);
	

}
