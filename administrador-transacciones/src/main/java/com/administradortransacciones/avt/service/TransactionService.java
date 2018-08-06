package com.administradortransacciones.avt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	private RepositoryContext repositoryContext;

	public List<Transaction> getTransactions() {
		Iterable<Transaction> results = repositoryContext.getDatabaseInstance("MYSQL").findAll();
		return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
	}
}
