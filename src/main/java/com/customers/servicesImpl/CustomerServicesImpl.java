package com.customers.servicesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customers.models.entity.Customer;
import com.customers.models.repository.CustomerRepository;
import com.customers.services.CustomerClientRequestService;
import com.customers.services.CustomerServiceMapper;
import com.customers.services.CustomerServices;
import com.customers.services.dto.CustomerDTO;
import com.customers.services.exceptions.ExceptionCustomService;
import com.customers.services.exceptions.RunTimeExceptionCustomService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServicesImpl implements CustomerServices {
	
	private CustomerRepository customerRepository;
	private CustomerServiceMapper customerMapper;
	private CustomerClientRequestService customerClientRequestService;
	
	public CustomerServicesImpl(CustomerRepository customerRepository,
								CustomerServiceMapper customerMapper,
								CustomerClientRequestService customerClientRequestService){
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
		this.customerClientRequestService = customerClientRequestService;
	}

	@Transactional
	@Override
	public List<CustomerDTO> save(CustomerDTO customers) {
		List<Customer> customerList = customerMapper.toEntity(Arrays.asList(customers));
		try {
			customerList = customerRepository.saveAll(customerList);	
		}catch (DataIntegrityViolationException | JpaSystemException e) {
			log.info("Error transaction", e);
			throw new RunTimeExceptionCustomService(e);
		}
		return  customerMapper.toDTO(customerList);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CustomerDTO> findAll(String filter) {
		List<Customer> customerList = new ArrayList<>();
		if(StringUtils.isBlank(filter)) {
			customerList = customerRepository.findAll();	
		}else {
			customerList = customerRepository.findAll()
					.stream()
					.filter(custom -> custom.getName().contains(filter)).toList();
		}
		return customerMapper.toDTO(customerList);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CustomerDTO> orderAll(String orderParam) {
		List<Customer >customerList = new ArrayList<>();
		if(StringUtils.isBlank(orderParam)) {
			customerList = customerRepository.findAll();	
		}else {
			if(orderParam.equalsIgnoreCase("name")) {
				customerList = customerRepository.findAll()
						.stream()
						.sorted(Comparator.comparing(Customer::getName))
						.toList();
			}else {
				customerList = customerRepository.findAll()
						.stream()
						.sorted(Comparator.comparingLong(customer -> Long.parseLong(customer.getCustomerId())))
						.toList();
			}
			
		}
		return customerMapper.toDTO(customerList);
	}
	
	@Transactional
	@Override
	public void delete(Long id) throws ExceptionCustomService {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
				throw new ExceptionCustomService(ExceptionCustomService.CUSTOMER_NOT_FOUND_STRING);
		}
		try {
			customerRepository.deleteAll(Arrays.asList(customer.get()));	
		}catch (DataIntegrityViolationException | JpaSystemException e) {
			log.info("Error transaction", e);
			throw new RunTimeExceptionCustomService(e);
		}
	}
	
	@Override
	public List<CustomerDTO> getFromServiceExternal(int serviceId) throws ExceptionCustomService, JsonProcessingException {
		List<CustomerDTO>  listCustomerDTO = customerClientRequestService.getCustomersFromService(serviceId);
		if(listCustomerDTO.isEmpty()) {
				throw new ExceptionCustomService(ExceptionCustomService.CUSTOMER_NOT_FOUND_STRING);
		}
		return listCustomerDTO;
	}

}
