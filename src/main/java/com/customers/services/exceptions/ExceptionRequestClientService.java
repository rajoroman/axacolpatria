package com.customers.services.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ExceptionRequestClientService extends RuntimeException {
	
	private static final long serialVersionUID = -5944006828038286308L;
	
	private String message;
	private int code;
	
	public ExceptionRequestClientService(String msg) {
		super(msg);
	}
	
	public ExceptionRequestClientService(Throwable e) {
		super(e);
	}
	
	public ExceptionRequestClientService(Throwable e,int code) {
		super();
		this.code = code;
	}
}
