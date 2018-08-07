package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.mongo.mapper.TransactionMapperMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.mapper.TransactionMapperMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@Service
public class TransactionService {

	@Autowired
	private RepositoryContext repositoryContext;

	public List<TransactionDto> getTransactions(int peso, String repository) {
		List<?> results = repositoryContext.getDatabaseInstance(repository).findAll();

		if (results.isEmpty()) {
			// TODO: return a message?
			return new ArrayList<>();
		}

		List<TransactionDto> list = new ArrayList<>();
		if (results.get(0) instanceof TransactionMySql) {
			results.stream().forEach(t -> list.add(TransactionMapperMySql.INSTANCE.transactionToTransactionDto((TransactionMySql) t)));
		} else if (results.get(0) instanceof TransactionMongo) {
			results.stream().forEach(t -> list.add(TransactionMapperMongo.INSTANCE.transactionToTransactionDto((TransactionMongo) t)));
		}
		return list;
	}
}
