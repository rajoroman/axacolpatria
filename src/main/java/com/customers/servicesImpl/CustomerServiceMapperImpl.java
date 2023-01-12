package com.customers.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.customers.models.entity.Customer;
import com.customers.services.CustomerServiceMapper;
import com.customers.services.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceMapperImpl implements CustomerServiceMapper {
	
	private static final String ERROR_MAPPER_TOENTITY = "Error mapping DTO to Entity";
	private static final String ERROR_MAPPER_TODTO = "Error mapping Entity to DTO" ;

	@Override
	public List<Customer> toEntity(List<CustomerDTO> customerDTOList) {
		ObjectMapper mapper = new ObjectMapper();
		List<Customer> result = new ArrayList<>();
		try {
			customerDTOList.forEach(customerDTO -> {
				Customer customer = mapper.convertValue(customerDTO, Customer.class);
				result.add(customer);
				});	
		}catch(Exception e) {
			log.info(ERROR_MAPPER_TOENTITY, e);
		}
		return result;
	}

	@Override
	public List<CustomerDTO> toDTO(List<Customer> customerList) {
		ObjectMapper mapper = new ObjectMapper();
		List<CustomerDTO> result = new ArrayList<>();
		try {
		customerList.forEach(customer -> {
			CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);
			result.add(customerDTO);
		});
		}catch(Exception e) {
			log.info(ERROR_MAPPER_TODTO, e);
		}
		return result;
	}

}
