package com.tamil.bookmyshow.util;

import org.modelmapper.ModelMapper;

import com.tamil.bookmyshow.dto.CustomerDTO;
import com.tamil.bookmyshow.entity.CustomerEntity;

public class Mapper {
private static ModelMapper mapper=new ModelMapper();
	private Mapper() {}
	
	public static CustomerDTO mapCustomer(CustomerEntity entity) {
		CustomerDTO dto=new CustomerDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
