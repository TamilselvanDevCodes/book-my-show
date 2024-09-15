package com.tamil.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.bookmyshow.entity.Theatre;
import com.tamil.bookmyshow.service.TheatreService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("theatre")
public class TheatreController {

	@Autowired
	private TheatreService service;
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Theatre>>save(@Valid @RequestBody Theatre theatre,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.save(theatre, adminEmail, adminPassword);
	}
	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<Theatre>>save(@RequestParam int id){
		return service.find(id);
	}
	@GetMapping("updateById")
	public ResponseEntity<ResponseStructure<Theatre>>update(@Valid @RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword,@RequestBody Theatre theatre,BindingResult res){
		return service.update(theatre, id, adminEmail, adminPassword);
	}
	@GetMapping("deleteById")
	public ResponseEntity<ResponseStructure<Theatre>>delete( @RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.delete(id, adminEmail, adminPassword);
	}

}
