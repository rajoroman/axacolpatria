package com.customers.services.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.TransactionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.customers.services.dto.CustomerDTO;
import com.customers.services.dto.FilterDTO;
import com.customers.services.dto.ResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

	private static final String DATA_EXIST = "Data already exists";
	
	private static final String CUSTOMER_EXIST = "Customers already exists";

	private static final String CONNECTION_CLOSE = "Error establishing a database connection";
	
	private static final String ERROR_FORMAT_JSON = "Error while extracting response from JSON";
	
	private static final String SERVICE_NOT_FOUND = "Service not found";
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseDTO> runtimeException(HttpServletRequest request, RuntimeException e) {

		HttpStatus httpStatus = HttpStatus.OK;
		String error_message = "";

		if (e.getCause() instanceof DataIntegrityViolationException) {
			httpStatus = HttpStatus.BAD_REQUEST;
			error_message = DATA_EXIST;

		} else if (e.getCause() instanceof TransactionException) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			error_message = CONNECTION_CLOSE;
		}else if (e.getCause() instanceof HttpMessageNotReadableException) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			error_message = ERROR_FORMAT_JSON; 
		}
		
		ResponseDTO response = ResponseDTO.builder()
				.hasError(true)
				.code(httpStatus.value())
				.message(error_message).build();
				
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
		
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        
        StringBuilder message = this.buildMessage(fieldErrors);
        
        ResponseDTO response = ResponseDTO.builder()
        		.hasError(true)
				.code(HttpStatus.OK.value())
				.message(message.toString()).build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@ExceptionHandler(BindException.class)
    public ModelAndView handleValidationErrors(BindException ex) {
		
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        
        StringBuilder message = this.buildMessage(fieldErrors);
        
        FilterDTO filterDTO = new FilterDTO("");
        CustomerDTO customerDTO = new CustomerDTO(); 
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("filter", filterDTO);
        modelAndView.addObject("hasError",1);
        modelAndView.addObject("customer", customerDTO);
        modelAndView.addObject("messageList", Arrays.asList(message));
		modelAndView.setViewName("index");
		modelAndView.setViewName("register");
		return modelAndView;
    }
	
	@ExceptionHandler(RunTimeExceptionCustomService.class)
    public ModelAndView handleValidationRunTimeErrors(RunTimeExceptionCustomService ex) {
		
		String error_message = "";
		
		if (ex.getCause() instanceof DataIntegrityViolationException) {
			error_message = CUSTOMER_EXIST;

		} else if (ex.getCause() instanceof TransactionException) {
			error_message = CONNECTION_CLOSE;
			
		} 
        
        FilterDTO filterDTO = new FilterDTO("");
        CustomerDTO customerDTO = new CustomerDTO(); 
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("filter", filterDTO);
        modelAndView.addObject("hasError",1);
        modelAndView.addObject("customer", customerDTO);
        modelAndView.addObject("messageList", error_message);
		modelAndView.setViewName("index");
		modelAndView.setViewName("register");
		return modelAndView;
    }
	
	private StringBuilder buildMessage(List<FieldError> fieldErrors) {
		StringBuilder message = new StringBuilder();
		fieldErrors.forEach(field ->{
			message.append(field.getDefaultMessage().toUpperCase().concat("\\n"));
		});
		return message;
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> notDataFoundException(HttpServletRequest request, Exception e) {
		ErrorInfo errorInfo = new ErrorInfo();
		HttpStatus httpStatus = HttpStatus.OK;
		errorInfo.setMessage("Unknown error");
		log.info(e.toString());
		return new ResponseEntity<>(errorInfo, httpStatus);
	}
	
	@ExceptionHandler(ExceptionRequestClientService.class)
    public ModelAndView handleErrorsRequestClient(ExceptionRequestClientService ex) {
		List<CustomerDTO> customerList = new ArrayList<>();
		
		String error_message = "";
		
		if (ex.getCode() == 404) {
			error_message = SERVICE_NOT_FOUND;
		}else if (ex.getCause() instanceof HttpMessageNotReadableException) {
			error_message = ERROR_FORMAT_JSON;
		} 
        
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customersList", customerList);
		modelAndView.addObject("filter", filterDTO);
		modelAndView.addObject("hasError",1);
	    modelAndView.addObject("messageList", error_message);
		modelAndView.setViewName("list");
		return modelAndView;
    }
	
	@ExceptionHandler(ExceptionCustomService.class)
    public ModelAndView handleErrorsRequestClient(ExceptionCustomService ex) {
		List<CustomerDTO> customerList = new ArrayList<>();
		
		FilterDTO filterDTO = new FilterDTO("");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customersList", customerList);
		modelAndView.addObject("filter", filterDTO);
		modelAndView.addObject("hasError",1);
	    modelAndView.addObject("messageList", ex.getMessage());
		modelAndView.setViewName("list");
		return modelAndView;
    }
	
}
