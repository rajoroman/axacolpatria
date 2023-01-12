package com.customers.services;

import java.util.List;

import com.customers.services.dto.CustomerDTO;
import com.customers.services.exceptions.ExceptionCustomService;

public interface CustomerServices {
	List<CustomerDTO> findAll(String filter);
	List<CustomerDTO> save(CustomerDTO customers) throws ExceptionCustomService;
	List<CustomerDTO> orderAll(String orderParam);
	void delete(Long id) throws ExceptionCustomService;
}
