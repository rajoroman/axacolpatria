package com.customers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.customers.services.CustomerServices;
import com.customers.services.dto.CustomerDTO;
import com.customers.services.dto.FilterDTO;
import com.customers.services.exceptions.ExceptionCustomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;

@Controller
public class MvcController {
	
	private static final String FILTER = "filter";
	
	private static final String CUSTOMERSLIST = "customersList";
	
	private static final String LIST = "list";
	
	private static final String HASERROR = "hasError";
	
	private static final String INDEX = "index";
	
	private static final String REGISTER = "register";

	@Autowired
	private CustomerServices customerServices;

	@RequestMapping("/")
	public String home() {
		return INDEX;
	}

	@GetMapping("/list")
	public ModelAndView customersList(@ModelAttribute("filter") FilterDTO filter) throws ExceptionCustomService, JsonMappingException, JsonProcessingException {
		List<CustomerDTO> customerList = customerServices.findAll(filter.getNameFilter());
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(CUSTOMERSLIST, customerList);
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.setViewName(LIST);
		return modelAndView;
	}
	
	@GetMapping("/listFromService")
	public ModelAndView customersListFrom(@ModelAttribute("serviceId") int serviceId) throws ExceptionCustomService, JsonMappingException, JsonProcessingException {
		List<CustomerDTO> customerList = customerServices.getFromServiceExternal(serviceId);
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(CUSTOMERSLIST, customerList);
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.setViewName(LIST);
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView customersCreate(@Valid @ModelAttribute("customer") CustomerDTO customerDTO) throws ExceptionCustomService{
		customerServices.save(customerDTO);
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.addObject(HASERROR,0);
		modelAndView.setViewName(INDEX);
		return modelAndView;
	}
	
	@RequestMapping("/registerForm")
	public ModelAndView registerForm(@ModelAttribute("hasError") String error) {
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		CustomerDTO customerDTO = new CustomerDTO(); 
		modelAndView.addObject("customer", customerDTO);
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.addObject(HASERROR,error);
		modelAndView.setViewName(REGISTER);
		return modelAndView;
	}
	
	@GetMapping("/order")
	public ModelAndView customersList(@ModelAttribute("orderParam") String orderParam) {
		List<CustomerDTO> customerList = customerServices.orderAll(orderParam);
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(CUSTOMERSLIST, customerList);
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.setViewName(LIST);
		return modelAndView;
	}
	
	@GetMapping("/delete")
	public ModelAndView customersDelete(@ModelAttribute("id") Long id) throws ExceptionCustomService {
		customerServices.delete(id);
		List<CustomerDTO> customerList = customerServices.findAll("");
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(CUSTOMERSLIST, customerList);
		modelAndView.addObject(FILTER, filterDTO);
		modelAndView.setViewName(LIST);
		return modelAndView;
	}
	


}
