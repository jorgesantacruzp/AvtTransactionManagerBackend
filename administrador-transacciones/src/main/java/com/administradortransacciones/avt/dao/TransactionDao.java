package com.administradortransacciones.avt.dao;

import java.util.List;

public interface TransactionDao<T> {

	void persist();

	List<T> findAll();

	List<T> findByWeight(int weight);

	void findByType();

	void delete();
}
