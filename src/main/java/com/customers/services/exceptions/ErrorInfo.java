package com.customers.services.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
	
	
	   public ErrorInfo(int statusCode, String message, String uri) {
	       this.message = message;
	       this.statusCode = statusCode;
	       this.uri = uri;
	       
	   }

	   @JsonProperty("message")
	   private String message;
	   
	   @JsonProperty("status_code")
	   private int statusCode;
	   
	   @JsonProperty("uri")
	   private String uri;
	  
	   
}
