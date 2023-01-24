package com.customers.services;

import java.util.List;

import com.customers.services.dto.CustomerDTO;

public interface CustomerClientRequestService {
	List<CustomerDTO> getCustomersFromService(int serviceId);
}
