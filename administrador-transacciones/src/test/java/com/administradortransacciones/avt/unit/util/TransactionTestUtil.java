package com.administradortransacciones.avt.unit.util;

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
}
