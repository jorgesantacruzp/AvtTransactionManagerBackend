package com.administradortransacciones.avt.dao;

import java.util.List;

public interface TransactionDao<T> {

	void persist();

	List<T> findAll();

	void findByWeight();

	void findByType();

	void delete();
}
