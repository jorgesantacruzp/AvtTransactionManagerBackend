package com.administradortransacciones.avt.common.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ApiBase {

	private List<TransactionDto> transactions;
	private String errorType;
	private String message;

	public List<TransactionDto> getTransactions() {
		return transactions;
	}

	public void setTransactions(final List<TransactionDto> transactions) {
		this.transactions = transactions;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(final String errorType) {
		this.errorType = errorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
