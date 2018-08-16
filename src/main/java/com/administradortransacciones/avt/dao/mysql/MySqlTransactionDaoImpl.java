package com.administradortransacciones.avt.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

@Repository
public class MySqlTransactionDaoImpl implements TransactionDao<TransactionMySql> {

	private MySqlTransactionRepository mysqlTransactionRepository;

	@Autowired
	public void setMysqlTransactionRepository(MySqlTransactionRepository mysqlTransactionRepository) {
		this.mysqlTransactionRepository = mysqlTransactionRepository;
	}

	@Override
	public TransactionMySql persist(final TransactionMySql transaction) {
		return mysqlTransactionRepository.save(transaction);
	}

	@Override
	public List<TransactionMySql> findAll() {
		final Iterable<TransactionMySql> results = mysqlTransactionRepository.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<TransactionMySql> findByWeight(final int weight) {
		final List<TransactionMySql> results = mysqlTransactionRepository.findByWeight(weight);
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<TransactionMySql> findByType(final String type) {
		final List<TransactionMySql> results = mysqlTransactionRepository.findByType(new TransactionTypeMySql(type));
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

	@Override
	public void delete(final String id) {
		mysqlTransactionRepository.deleteById(Long.valueOf(id));
	}

	@Override
	public int count() {
		return (int) mysqlTransactionRepository.count();
	}

	/**
	 * if transaction does not exist NoSuchElementException is thrown
	 */
	@Override
	public TransactionMySql findById(final String id) {
		Optional<TransactionMySql> optional = mysqlTransactionRepository.findById(Long.valueOf(id));
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new NoSuchElementException();
	}

	@Override
	public List<TransactionMySql> findByWeightAndType(final int weight, final String type) {
		final List<TransactionMySql> results = mysqlTransactionRepository.findByWeightAndType(weight,
				new TransactionTypeMySql(type));
		if (results == null) {
			return new ArrayList<>();
		}
		return results;
	}

}
