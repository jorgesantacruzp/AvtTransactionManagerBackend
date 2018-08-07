package com.administradortransacciones.avt.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@Repository
public class MySqlRepository implements TransactionDao<TransactionMySql> {

	@Autowired
	private MySqlTransactionRepository txnRepo;

	@Override
	public void persist() {

	}

	@Override
	public List<TransactionMySql> findAll() {
		Iterable<TransactionMySql> results = txnRepo.findAll();
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
