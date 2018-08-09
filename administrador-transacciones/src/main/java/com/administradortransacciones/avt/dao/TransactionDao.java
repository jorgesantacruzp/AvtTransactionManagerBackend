package com.administradortransacciones.avt.dao;

import java.util.List;

public interface TransactionDao<T> {

	T persist(T t);

	List<T> findAll();

	List<T> findByWeight(int weight);

	List<T> findByType(String type);

	T findById(String id);

	void delete();

	int count();
}
