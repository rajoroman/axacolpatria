package com.customers.services;

import java.util.List;

import com.customers.models.entity.Customer;
import com.customers.services.dto.CustomerDTO;

public interface CustomerServiceMapper {
	List<Customer> toEntity(List<CustomerDTO> customersList);
	List<CustomerDTO> toDTO(List<Customer> customersDTOList);
}
