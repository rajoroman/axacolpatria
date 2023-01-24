package com.customers.services.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RunTimeExceptionCustomService extends RuntimeException {
	
	private static final long serialVersionUID = -5944006828038286308L;
	
	private String message;
	private int code;
	
	public RunTimeExceptionCustomService(String msg) {
		super(msg);
	}
	
	public RunTimeExceptionCustomService(Throwable e) {
		super(e);
	}
	
	public RunTimeExceptionCustomService(Throwable e,int code) {
		super();
		this.code = code;
	}
}
