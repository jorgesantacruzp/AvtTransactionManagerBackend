package com.administradortransacciones.avt.common;

public enum ErrorCodesEnum {

	ATXN_TRANSACTION_NOT_SAVED("ATXN_TRANSACTION_NOT_SAVED"),
	ATXN_TRANSACTION_NOT_FETCHED("ATXN_TRANSACTION_NOT_FETCHED"),
	ATXN_TRANSACTION_NOT_UPDATED("ATXN_TRANSACTION_NOT_UPDATED"),
	ATXN_TRANSACTION_NOT_DELETED("ATXN_TRANSACTION_NOT_DELETED"),
	ATXN_TRANSACTION_NOT_FOUND("ATXN_TRANSACTION_NOT_FOUND");

	private String code;

	private ErrorCodesEnum(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
