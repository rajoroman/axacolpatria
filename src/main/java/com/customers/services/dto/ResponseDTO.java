package com.customers.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
	private boolean hasError;
	private Integer code;
	private String  message;
	private Object  data;
}
