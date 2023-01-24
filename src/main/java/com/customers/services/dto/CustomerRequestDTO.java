package com.customers.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	private String phoneNumber;

}
