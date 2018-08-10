package com.administradortransacciones.avt.unit.util;

import java.util.ArrayList;
import java.util.List;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionTypeMongo;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

public class TransactionTestUtil {

	public static TransactionMySql getTransactionMySqlSample() {
		final TransactionMySql transaction = new TransactionMySql();
		transaction.setId(1L);
		transaction.setName("Sample transaction");
		transaction.setType(new TransactionTypeMySql());
		transaction.setWeight(10);
		return transaction;
	}

	public static TransactionMongo getTransactionMongoSample() {
		final TransactionMongo transaction = new TransactionMongo();
		transaction.setId("123456");
		transaction.setName("Sample transaction");
		transaction.setType(new TransactionTypeMongo());
		transaction.setWeight(10);
		return transaction;
	}

	public static TransactionDto getTransactionDtoSample() {
		final TransactionDto transaction = new TransactionDto();
		transaction.setId("123456");
		transaction.setName("Sample transaction");
		transaction.setType(TransactionTypeEnum.CHECK_CHANGE.name());
		transaction.setWeight(10);
		return transaction;
	}

	public static List<TransactionMySql> getTransactionsMySql() {
		final List<TransactionMySql> transactions = new ArrayList<>();
		transactions.add(getTransactionMySqlSample());
		return transactions;
	}

	public static List<TransactionMongo> getTransactionsMongo() {
		final List<TransactionMongo> transactions = new ArrayList<>();
		transactions.add(getTransactionMongoSample());
		return transactions;
	}
}
