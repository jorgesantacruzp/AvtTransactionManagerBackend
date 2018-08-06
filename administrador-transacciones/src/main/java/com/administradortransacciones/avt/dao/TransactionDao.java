package com.administradortransacciones.avt.dao;

import com.administradortransacciones.avt.dao.model.Transaction;

public interface TransactionDao {

	void persist();

	Iterable<Transaction> findAll();

	void findByWeight();

	void findByType();

	void delete();
}
