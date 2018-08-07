package com.administradortransacciones.avt.dao.mongo.mapper.util;

import com.administradortransacciones.avt.dao.mongo.model.TransactionTypeMongo;

public class TransactionMapperUtilMongo {

	@TransactionTypeToStringMongo
	public String first(final TransactionTypeMongo type) {
		return type.getName();
	}
}
