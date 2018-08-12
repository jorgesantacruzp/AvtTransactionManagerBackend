package com.administradortransacciones.avt.common.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.dto.ApiBase;
import com.administradortransacciones.avt.common.exception.TransactionException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@Autowired
	private Environment environment;

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ApiBase> handleConflict(final TransactionException e) {
		final ApiBase response = new ApiBase();
		final ErrorCodesEnum errorCode = e.getErrorCode();
		response.setErrorType(errorCode.getCode());
		response.setMessage(environment.getProperty(errorCode.getPropertyCode()));
		return new ResponseEntity<ApiBase>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
