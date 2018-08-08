package com.administradortransacciones.avt.common;

public enum ErrorCodesEnum {

	ATXN_TRANSACTION_NOT_SAVED("ATXN_TRANSACTION_NOT_SAVED");

	private String code;

	private ErrorCodesEnum(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
