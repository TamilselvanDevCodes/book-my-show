package com.tamil.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tamil.bookmyshow.dao.AdminDAO;
import com.tamil.bookmyshow.dto.AdminDTO;
import com.tamil.bookmyshow.entity.AdminEntity;
import com.tamil.bookmyshow.exception.AdminNotFoundException;
import com.tamil.bookmyshow.exception.PasswordException;
import com.tamil.bookmyshow.util.ResponseStructure;

@Service
public class AdminService {
	@Autowired
	private  AdminDAO dao;
	
	private ModelMapper mapper=new ModelMapper();
	
	public ResponseEntity<ResponseStructure<AdminDTO>> save(AdminEntity admin) {
		if(admin==null)throw new AdminNotFoundException("Admin is null");
        ResponseStructure<AdminDTO> str=new ResponseStructure<>();
		AdminDTO dto=new AdminDTO();
		dao.save(admin);
		mapper.map(admin, dto);
		str.setMessage("Admin Saved Successfully");
		str.setStatus(HttpStatus.CREATED.value());
		str.setObject(dto);
		return new ResponseEntity<>(str, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<AdminDTO>> find(String email) {
        AdminEntity entity=dao.find(email);
		if(entity==null)throw new AdminNotFoundException("Admin not found with the Given email "+email);
		AdminDTO dto=new AdminDTO();
		mapper.map(entity,dto);
		ResponseStructure<AdminDTO> str=new ResponseStructure<>();
		str.setMessage("Admin Successfully Fetched");
		str.setStatus(HttpStatus.FOUND.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<AdminDTO>>(str,HttpStatus.FOUND);
}
	private AdminEntity find(long contactNumber) {
		AdminEntity entity=dao.find(contactNumber);
		if(entity==null)throw new AdminNotFoundException("Admin not found with the Given Contact Number "+contactNumber);
		return entity;
}
	public ResponseEntity<ResponseStructure<AdminDTO>> changePassword(long contactNumber,String newPassword) {
		AdminEntity entity=find(contactNumber);
		entity.setPassword(newPassword);
		dao.save(entity);
		AdminDTO dto=new AdminDTO();
		mapper.map(entity,dto);
		ResponseStructure<AdminDTO> str=new ResponseStructure<>();
		str.setMessage("Admin Password Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<AdminDTO>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<AdminDTO>> update(AdminEntity admin,String email) {
		if(admin==null)throw new AdminNotFoundException("Admin is null");
		AdminEntity entity=dao.update(admin,email);
		if(entity==null)throw new AdminNotFoundException("Admin not found with the given email "+email+" to update");
		AdminDTO dto=new AdminDTO();
		mapper.map(entity,dto);
		ResponseStructure<AdminDTO> str=new ResponseStructure<>();
		str.setMessage("Admin Successfully Updated");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<AdminDTO>>(str,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<AdminDTO>> delete(String email) {
		AdminEntity entity=dao.delete(email);
		if(entity==null)throw new AdminNotFoundException("Admin not found with the given email "+email+" to delete");
		AdminDTO dto=new AdminDTO();
		mapper.map(entity,dto);
		ResponseStructure<AdminDTO> str=new ResponseStructure<>();
		str.setMessage("Admin Successfully Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setObject(dto);
		return new ResponseEntity<ResponseStructure<AdminDTO>>(str,HttpStatus.OK);
	}
	public   void adminLogin(String email,String password) {
	
		AdminEntity a= dao.find(email);
		if(a==null)throw new AdminNotFoundException("Admin not found with the given Email :"+email);
		if(!a.getPassword().equals(password))throw new PasswordException("Incorrect Password fo admin :"+email);
			}
}