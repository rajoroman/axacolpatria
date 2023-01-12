package com.customers.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customers.services.CustomerServices;
import com.customers.services.dto.CustomerDTO;
import com.customers.services.dto.ResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerControllers {
	
	private static final String SUCCESS_REQUEST="Petición procesada correctamente"; 
	
	@Autowired
	private CustomerServices customerServices;
	
	@PostMapping
	
	public ResponseEntity<ResponseDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTOlist) throws Exception {
		customerServices.save(customerDTOlist);	
		ResponseDTO response = ResponseDTO.builder()
								.code(HttpStatus.OK.value())
								.message(SUCCESS_REQUEST).build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getReport() throws Exception {
		List<CustomerDTO> customerReport = customerServices.findAll("");	
		ResponseDTO response = ResponseDTO.builder()
				.code(HttpStatus.OK.value())
				.data(customerReport).build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
