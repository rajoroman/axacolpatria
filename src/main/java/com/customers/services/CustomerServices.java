package com.customers.services;

import java.util.List;

import com.customers.services.dto.CustomerDTO;
import com.customers.services.exceptions.ExceptionCustomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface CustomerServices {
	List<CustomerDTO> findAll(String filter);
	List<CustomerDTO> save(CustomerDTO customers) throws ExceptionCustomService;
	List<CustomerDTO> orderAll(String orderParam);
	void delete(Long id) throws ExceptionCustomService;
	List<CustomerDTO> getFromServiceExternal(int serviceId) throws ExceptionCustomService, JsonMappingException, JsonProcessingException;
}
