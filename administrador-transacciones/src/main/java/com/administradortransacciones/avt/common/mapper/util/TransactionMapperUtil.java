package com.administradortransacciones.avt.common.mapper.util;

import com.administradortransacciones.avt.dao.model.TransactionType;

public class TransactionMapperUtil {

	@TransactionTypeToString
	public String first(final TransactionType type) {
		return type.getName();
	}
}
