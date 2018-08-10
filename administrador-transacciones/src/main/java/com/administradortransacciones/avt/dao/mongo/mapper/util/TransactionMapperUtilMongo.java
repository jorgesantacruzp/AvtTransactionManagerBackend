package com.administradortransacciones.avt.dao.mongo.mapper.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.administradortransacciones.avt.common.mapper.util.CurrentDate;
import com.administradortransacciones.avt.dao.mongo.model.TransactionTypeMongo;

@Component
public class TransactionMapperUtilMongo {

	@TransactionTypeToStringMongo
	public String entityToString(final TransactionTypeMongo type) {
		return type.getName();
	}

	@CurrentDate
	public Date addCurrentDate(final String createdDate) {
		return new Date();
	}
}
