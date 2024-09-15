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

import com.tamil.bookmyshow.dto.AdminDTO;
import com.tamil.bookmyshow.entity.AdminEntity;
import com.tamil.bookmyshow.service.AdminService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AdminService service;
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<AdminDTO>>save(@Valid @RequestBody AdminEntity admin,BindingResult res){
		return service.save(admin);
	}
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<AdminDTO>>save( @RequestParam String email){
		return service.find(email);
	}
	@GetMapping("changePassword")
	public ResponseEntity<ResponseStructure<AdminDTO>>changePassword( @RequestParam long contactNumber,@RequestParam String newPassword){
		return service.changePassword(contactNumber, newPassword);
	}
	@PostMapping("updateByEmail")
	public ResponseEntity<ResponseStructure<AdminDTO>>update(@Valid @RequestBody AdminEntity admin,@RequestParam String email,BindingResult res){
		return service.update(admin,email);
	}
	@GetMapping("deleteByEmail")
	public ResponseEntity<ResponseStructure<AdminDTO>>delete(@RequestParam String email){
		return service.delete(email);
	}
}
