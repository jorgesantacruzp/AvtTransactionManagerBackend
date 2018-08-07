package com.administradortransacciones.avt.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

@Repository
public class MongoDbRepository implements TransactionDao<TransactionMongo> {

	@Autowired
	private MongoDbTransactionRepository transactionRepository;

	@Override
	public void persist() {

	}

	@Override
	public List<TransactionMongo> findAll() {
		List<TransactionMongo> results = transactionRepository.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public void findByWeight() {

	}

	@Override
	public void findByType() {

	}

	@Override
	public void delete() {

	}

}
