package com.administradortransacciones.avt.dao.mysql.mapper.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.mapper.util.CurrentDate;
import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

@Component
public class TransactionMapperUtilMySql {

	@TransactionTypeToStringMySql
	public String entityToString(final TransactionTypeMySql type) {
		return type == null ? null : type.getName();
	}

	@StringToTransactionTypeMySql
	public Long stringToEntity(final String type) {
		return Long.valueOf(TransactionTypeEnum.findByName(type).getId());
	}

	@CurrentDate
	public Date addCurrentDate(final String createdDate) {
		return new Date();
	}
}
