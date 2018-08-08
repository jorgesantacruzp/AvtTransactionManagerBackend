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
	private MongoDbTransactionRepository mongoTransactionRepository;

	@Override
	public TransactionMongo persist(TransactionMongo transaction) {
		return mongoTransactionRepository.save(transaction);
	}

	@Override
	public List<TransactionMongo> findAll() {
		List<TransactionMongo> results = mongoTransactionRepository.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMongo> findByWeight(final int weight) {
		List<TransactionMongo> results = mongoTransactionRepository.findByWeight(weight);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMongo> findByType(final String type) {
		List<TransactionMongo> results = mongoTransactionRepository.findByTypeName(type);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public void delete() {

	}

}
