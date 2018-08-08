package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mongo.mapper.TransactionMapperMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.mapper.TransactionMapperMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@Service
public class TransactionService {

	@Autowired
	private RepositoryContext repositoryContext;

	public List<TransactionDto> getTransactions(final String repository, final int type) {
		List<?> results = Collections.emptyList();
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
			return Collections.emptyList();
		}

		final List<TransactionDto> list = new ArrayList<>();
		if (results.get(0) instanceof TransactionMySql) {
			results.stream().forEach(t -> list.add(TransactionMapperMySql.INSTANCE.transactionToTransactionDto((TransactionMySql) t)));
		} else if (results.get(0) instanceof TransactionMongo) {
			results.stream().forEach(t -> list.add(TransactionMapperMongo.INSTANCE.transactionToTransactionDto((TransactionMongo) t)));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public TransactionDto saveTransaction(final String repository, final TransactionDto request) {
		TransactionDto result = new TransactionDto();
		try {
			TransactionDao<?> dbInstance = repositoryContext.getDatabaseInstance(repository);
			if (RepositoryEnum.MYSQL.name().equals(repository)) {
				TransactionMySql mySqlEntity = TransactionMapperMySql.INSTANCE.transactionDtoToTransactionMysql(request);
				((TransactionDao<TransactionMySql>) dbInstance).persist(mySqlEntity);
			} else if (RepositoryEnum.MONGODB.name().equals(repository)) {
				TransactionMongo mongoEntity = TransactionMapperMongo.INSTANCE.transactionDtoToTransactionMongo(request);
				((TransactionDao<TransactionMongo>) dbInstance).persist(mongoEntity);
			}
		} catch (Exception ex) {
			// TODO: LOG ERRORS
			result.setErrorType(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED.getCode());
			result.setMessage("There was an error when trying to save transaction");
		}
		return result;
	}
}
