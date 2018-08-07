package com.administradortransacciones.avt.dao.mysql.mapper.util;

import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

public class TransactionMapperUtilMySql {

	@TransactionTypeToStringMySql
	public String first(final TransactionTypeMySql type) {
		return type.getName();
	}
}
