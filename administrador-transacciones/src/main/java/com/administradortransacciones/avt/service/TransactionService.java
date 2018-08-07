package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
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

	public List<TransactionDto> getTransactions(final String repository, final int type) {
		List<?> results = null;
		TransactionTypeEnum typeEnum = TransactionTypeEnum.findById(type);
		if (TransactionTypeEnum.ALL.equals(typeEnum)) {
			results = repositoryContext.getDatabaseInstance(repository).findAll();
		} else {
			results = repositoryContext.getDatabaseInstance(repository).findByType(typeEnum.name());
		}
		return buildTransacionDtoList(results);
	}

	public List<TransactionDto> findTransactionByWeight(final String repository, final int weight, final String type) {
		final List<?> results = repositoryContext.getDatabaseInstance(repository).findByWeight(weight);
		return buildTransacionDtoList(results);
	}

	private List<TransactionDto> buildTransacionDtoList(final List<?> results) {
		if (results.isEmpty()) {
			return new ArrayList<>();
		}

		final List<TransactionDto> list = new ArrayList<>();
		if (results.get(0) instanceof TransactionMySql) {
			results.stream().forEach(t -> list.add(TransactionMapperMySql.INSTANCE.transactionToTransactionDto((TransactionMySql) t)));
		} else if (results.get(0) instanceof TransactionMongo) {
			results.stream().forEach(t -> list.add(TransactionMapperMongo.INSTANCE.transactionToTransactionDto((TransactionMongo) t)));
		}
		return list;
	}
}
