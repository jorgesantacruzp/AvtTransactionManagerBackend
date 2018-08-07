package com.administradortransacciones.avt.dao.relational;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.model.Transaction;

@Repository
public class MySqlRepository implements TransactionDao {

	@Autowired
	private MySqlTransactionRepository txnRepo;

	@Override
	public void persist() {

	}

	@Override
	public List<Transaction> findAll() {
		Iterable<Transaction> results = txnRepo.findAll();
		if (results == null) {
			return new ArrayList<>();
		}
		return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
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
