package com.administradortransacciones.avt.dao;

import java.util.List;

import com.administradortransacciones.avt.dao.model.Transaction;

public interface TransactionDao {

	void persist();

	List<Transaction> findAll();

	void findByWeight();

	void findByType();

	void delete();
}
