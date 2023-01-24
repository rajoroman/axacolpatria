package com.customers.servicesImpl.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.customers.services.CustomerClientRequestService;
import com.customers.services.dto.CustomerDTO;
import com.customers.services.dto.CustomerRequestDTO;
import com.customers.services.exceptions.ExceptionRequestClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerClientRequestImpl implements CustomerClientRequestService {
	
	private static final String URL_SERVICE = "https://8e7c6b8a-fc46-4674-a529-4ebec57295d3.mock.pstmn.io/customers";
	
	private static final String URL_SERVICE_OK = "https://bb84a8de-4665-493a-b6f9-58e02f0ac763.mock.pstmn.io/customerOK";
	
	private final RestTemplate restTemplate;

	public CustomerClientRequestImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}

	@Override
	public List<CustomerDTO> getCustomersFromService(int serviceId) {
		
		String url = (serviceId == 1) ? URL_SERVICE : URL_SERVICE_OK;
		
		try {
			List<CustomerDTO> listCustomerDTO = new ArrayList<>();
			List<CustomerRequestDTO> listCustomerRequestDTO = Arrays.asList(this.restTemplate.getForObject(url, CustomerRequestDTO[].class));
			listCustomerRequestDTO.forEach(crDTO -> {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setAddress(crDTO.getAddress());
				customerDTO.setAge(crDTO.getAge());
				customerDTO.setCustomerId("0");
				customerDTO.setId(0L);
				customerDTO.setName(crDTO.getName());
				customerDTO.setPhoneNumber(crDTO.getPhoneNumber());
				listCustomerDTO.add(customerDTO);
			});
			return listCustomerDTO;
		}catch(HttpClientErrorException e) {
			log.info(e.toString());
			throw new ExceptionRequestClientService(e,e.getStatusCode().value());
		}catch(RestClientException e) {
			log.info(e.toString());
			throw new ExceptionRequestClientService(e.getCause());
		}
	}
}
