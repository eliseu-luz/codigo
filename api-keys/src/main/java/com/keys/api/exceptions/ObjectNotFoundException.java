package com.keys.api.exceptions;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	private String msg;

	public ObjectNotFoundException ( String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ObjectNotFoundException ( String msg, Throwable cause) {
		super(msg,cause);
		this.msg = msg;
	}
}
