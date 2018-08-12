package com.administradortransacciones.avt.common.exception;

import com.administradortransacciones.avt.common.ErrorCodesEnum;

public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCodesEnum errorCode;

	public TransactionException() {
		super();
	}

	public TransactionException(final ErrorCodesEnum errorCode) {
		super(errorCode.getCode());
		this.errorCode = errorCode;
	}

	public ErrorCodesEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final ErrorCodesEnum errorPropertyCode) {
		errorCode = errorPropertyCode;
	}

}
