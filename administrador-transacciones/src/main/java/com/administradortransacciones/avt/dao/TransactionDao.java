package com.administradortransacciones.avt.dao;

public interface TransactionDao {

	void persist();

	void findAll();

	void findByWeight();

	void findByType();

	void delete();
}
