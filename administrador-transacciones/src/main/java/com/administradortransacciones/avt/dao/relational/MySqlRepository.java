package com.administradortransacciones.avt.dao.relational;

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
	public Iterable<Transaction> findAll() {
		return txnRepo.findAll();
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
