package com.administradortransacciones.avt.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

@Repository
public class MongoDbTransactionDaoImpl implements TransactionDao<TransactionMongo> {

	@Autowired
	private MongoDbTransactionRepository mongoTransactionRepository;

	@Override
	public TransactionMongo persist(final TransactionMongo transaction) {
		return mongoTransactionRepository.save(transaction);
	}

	@Override
	public List<TransactionMongo> findAll() {
		final List<TransactionMongo> results = mongoTransactionRepository.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMongo> findByWeight(final int weight) {
		final List<TransactionMongo> results = mongoTransactionRepository.findByWeight(weight);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMongo> findByType(final String type) {
		final List<TransactionMongo> results = mongoTransactionRepository.findByTypeName(type);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public void delete(final String id) {
		mongoTransactionRepository.deleteById(id);
	}

	@Override
	public int count() {
		return Long.valueOf(mongoTransactionRepository.count()).intValue();
	}

	/**
	 * if transaction does not exist NoSuchElementException is thrown
	 */
	@Override
	public boolean exists(final String id) {
		return mongoTransactionRepository.findById(id).get() != null;
	}

}
