package com.customers.services.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ExceptionCustomService extends Exception {
	
	private static final long serialVersionUID = -5944006828038286308L;
	
	public static final int CUSTOMER_NOT_FOUND = 1;
	public static final int CUSTOMER_ALREADY_EXISTIS = 2;
	public static final int ACCOUNT_TYPE_NOT_FOUND = 3;
	public static final int INSUFFICIENT_BALANCE = 4;
	public static final String CUSTOMER_NOT_FOUND_STRING = "Customer not found";
	public static final String NOT_DATA_TO_DISPLAY_STRING = "No data to display";
	
	private String message;
	private int code;
	
	public ExceptionCustomService(String msg) {
		super(msg);
		this.message = msg;
	}
	
	public ExceptionCustomService(Throwable e) {
		super(e);
	}
	
	public ExceptionCustomService(Throwable e,int code) {
		super();
		this.code = code;
	}
}
