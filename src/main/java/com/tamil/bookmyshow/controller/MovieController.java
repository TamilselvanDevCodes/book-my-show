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

import com.tamil.bookmyshow.entity.Movie;
import com.tamil.bookmyshow.service.MovieService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("movie")
public class MovieController {
	@Autowired
	private MovieService service;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Movie>> save(@Valid @RequestBody Movie movie,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.save(movie, adminEmail, adminPassword);
	}
	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<Movie>> find(@RequestParam int id){
		return service.find(id);
	}
	@GetMapping("findByName")
	public ResponseEntity<ResponseStructure<Movie>> find( @RequestParam String movieName){
		return service.find(movieName);
	}
	@PostMapping("updateById")
	public ResponseEntity<ResponseStructure<Movie>> update(@Valid @RequestBody Movie movie,@RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.update(movie, id, adminEmail, adminPassword);
	}
	@PostMapping("updateByName")
	public ResponseEntity<ResponseStructure<Movie>> update(@Valid @RequestBody Movie movie,@RequestParam String movieName,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.update(movie, movieName, adminEmail, adminPassword);
	}
	
	@GetMapping("addArtist")
	public ResponseEntity<ResponseStructure<Movie>> addArtist( @RequestParam String movieName,@RequestParam String artistName,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.addArtist(movieName, artistName, adminEmail, adminPassword);
	}
	@GetMapping("removeArtist")
	public ResponseEntity<ResponseStructure<Movie>> removeArtist(@RequestParam String movieName,@RequestParam String artistName,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.removeArtist(movieName, artistName, adminEmail, adminPassword);
	}
	@GetMapping("deleteById")
	public ResponseEntity<ResponseStructure<Movie>> delete(@RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword){
		return service.delete(id, adminEmail, adminPassword);
	}	
	@GetMapping("deleteByName")
	public ResponseEntity<ResponseStructure<Movie>> delete(@Valid @RequestParam String movieName,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
		return service.delete(movieName, adminEmail, adminPassword);
	}
}
