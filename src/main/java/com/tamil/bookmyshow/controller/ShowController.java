package com.tamil.bookmyshow.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.bookmyshow.entity.Show;
import com.tamil.bookmyshow.service.ShowService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("show")
public class ShowController {

	@Autowired
	private ShowService service;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Show>>save( @Valid @RequestBody Show show,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.save(show, adminEmail, adminPassword);
	}
	@GetMapping("updateById")
	public ResponseEntity<ResponseStructure<Show>>update(  @RequestParam int id,@RequestParam LocalDate newDateOfStart,@RequestParam LocalTime newTimeOfStart,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.update(id, newDateOfStart, newTimeOfStart, adminEmail, adminPassword);
	} 
	@GetMapping("deleteById")
	public ResponseEntity<ResponseStructure<Show>>delete(  @RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.delete(id, adminEmail, adminPassword);
	}
}
