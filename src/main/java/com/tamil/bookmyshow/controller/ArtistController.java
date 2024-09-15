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

import com.tamil.bookmyshow.entity.Artist;

import com.tamil.bookmyshow.service.ArtistService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("artist")
public class ArtistController {
@Autowired
private ArtistService service;

@PostMapping("save")
public ResponseEntity<ResponseStructure<Artist>>save(@Valid @RequestBody Artist artist,@RequestParam String adminEmail,@RequestParam String adminPassword, BindingResult res){
	return service.save(artist, adminEmail, adminPassword);
}
@GetMapping("find")
public ResponseEntity<ResponseStructure<Artist>>find( @RequestParam int id){
	return service.find(id);
}
@GetMapping("findByName")
public ResponseEntity<ResponseStructure<Artist>>find( @RequestParam String artistName){
	return service.find(artistName);
}
@GetMapping("addMovie")
public ResponseEntity<ResponseStructure<Artist>> addArtist( @RequestParam String artistName,@RequestParam String movieName,@RequestParam String adminEmail,@RequestParam String adminPassword){
	return service.addMovie(artistName, movieName, adminEmail, adminPassword);
}
@GetMapping("removeMovie")
public ResponseEntity<ResponseStructure<Artist>> removeMovie( @RequestParam String artistName,@RequestParam String movieName,@RequestParam String adminEmail,@RequestParam String adminPassword){
	return service.removeMovie(artistName, movieName, adminEmail, adminPassword);
}
@PostMapping("updateById")
public ResponseEntity<ResponseStructure<Artist>> update(@Valid @RequestBody Artist artist,@RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
	return service.update(artist, id, adminEmail, adminPassword);
}
@PostMapping("updateByName")
public ResponseEntity<ResponseStructure<Artist>> update(@Valid @RequestBody Artist artist,@RequestParam String artistName,@RequestParam String adminEmail,@RequestParam String adminPassword,BindingResult res){
	return service.update(artist, artistName, adminEmail, adminPassword);
}
@GetMapping("deleteById")
public ResponseEntity<ResponseStructure<Artist>> delete(@RequestParam int id,@RequestParam String adminEmail,@RequestParam String adminPassword){
	return service.delete(id, adminEmail, adminPassword);
}	
@GetMapping("deleteByName")
public ResponseEntity<ResponseStructure<Artist>> delete( @RequestParam String artistName,@RequestParam String adminEmail,@RequestParam String adminPassword){
	return service.delete(artistName, adminEmail, adminPassword);
}
}
