package com.administradortransacciones.avt.common;

public enum ErrorCodesEnum {

	ATXN_TRANSACTION_NOT_SAVED("ATXN_TRANSACTION_NOT_SAVED", "atxn.error.transaction.not.saved"),
	ATXN_TRANSACTION_NOT_FETCHED("ATXN_TRANSACTION_NOT_FETCHED", "atxn.error.transaction.not.fetched"),
	ATXN_TRANSACTION_NOT_UPDATED("ATXN_TRANSACTION_NOT_UPDATED", "atxn.error.transaction.not.updated"),
	ATXN_TRANSACTION_NOT_DELETED("ATXN_TRANSACTION_NOT_DELETED", "atxn.error.transaction.not.deleted"),
	ATXN_TRANSACTION_NOT_FOUND("ATXN_TRANSACTION_NOT_FOUND", "atxn.error.transaction.not.found");

	private String code;
	private String propertyCode;

	private ErrorCodesEnum(final String code, final String propertyCode) {
		this.code = code;
		this.propertyCode = propertyCode;
	}

	public String getCode() {
		return code;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

}
