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
import com.tamil.bookmyshow.dto.CustomerDTO;
import com.tamil.bookmyshow.entity.CustomerEntity;
import com.tamil.bookmyshow.service.CustomerService;
import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<CustomerDTO>> save(@Valid @RequestBody CustomerEntity customer,BindingResult res){
	return	service.save(customer);
	}
	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<CustomerDTO>> find(@RequestParam int id){
	return	service.find(id);
	}
	@PostMapping("updateById")
	public ResponseEntity<ResponseStructure<CustomerDTO>> update(@Valid @RequestBody CustomerEntity customer,@RequestParam int id,@RequestParam String customerEmail,@RequestParam String customerPassword,BindingResult res){
	return	service.update(customer, customerEmail, customerPassword);
	}
	@GetMapping("deleteById")
	public ResponseEntity<ResponseStructure<CustomerDTO>> delete(@RequestParam String customerEmail,String customerPassword){
	return	service.delete(customerEmail, customerPassword);
	}
	@GetMapping("deleteTicketById")
	public ResponseEntity<ResponseStructure<CustomerDTO>>delete(int id,@RequestParam String email,@RequestParam String password){
		return service.cancelTicket(id, email, password);
	}
	
}
