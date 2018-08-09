package com.administradortransacciones.avt.dao;

import java.util.List;

public interface TransactionDao<T> {

	T persist(T t);

	List<T> findAll();

	List<T> findByWeight(int weight);
	
	List<T> findByWeightAndType(int weight, String type);

	List<T> findByType(String type);

	boolean exists(String id);

	void delete(String id);

	int count();
}
