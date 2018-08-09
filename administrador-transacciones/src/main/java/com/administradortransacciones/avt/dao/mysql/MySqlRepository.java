package com.administradortransacciones.avt.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

@Repository
public class MySqlRepository implements TransactionDao<TransactionMySql> {

	@Autowired
	private MySqlTransactionRepository mysqlTransactionRepository;

	@Override
	public TransactionMySql persist(final TransactionMySql transaction) {
		return mysqlTransactionRepository.save(transaction);
	}

	@Override
	public List<TransactionMySql> findAll() {
		Iterable<TransactionMySql> results = mysqlTransactionRepository.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<TransactionMySql> findByWeight(final int weight) {
		List<TransactionMySql> results = mysqlTransactionRepository.findByWeight(weight);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMySql> findByType(final String type) {
		List<TransactionMySql> results = mysqlTransactionRepository.findByType(new TransactionTypeMySql(type));
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public void delete() {

	}

	@Override
	public int count() {
		return Long.valueOf(mysqlTransactionRepository.count()).intValue();
	}

}
