package com.tamil.bookmyshow.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.dto.SeatType;
import com.tamil.bookmyshow.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer>{
		Optional<Seat>findBySeatType(SeatType seatType);
}
