package com.tamil.bookmyshow.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.bookmyshow.dto.SeatType;
import com.tamil.bookmyshow.entity.Ticket;

import com.tamil.bookmyshow.service.TicketService;
import com.tamil.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("ticket")
public class TicketController {
	@Autowired
	private TicketService service;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<List<Ticket>>> save(@RequestParam int showId,@RequestParam SeatType seatType,@RequestParam int numberOfSeats,@RequestParam String customerEmail,@RequestParam String customerPassword){
	return	service.save(showId, seatType, numberOfSeats, customerEmail, customerPassword);
	}
	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<Ticket>> find(@RequestParam int id){
	return	service.find(id);
	}
	@PostMapping("updateById")
	public ResponseEntity<ResponseStructure<Ticket>> find(@RequestBody Ticket ticket,@RequestParam int id){
	return	service.update(ticket, id);
	}
	
}
