package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.dao.mongo.mapper.TransactionMapperMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.mapper.TransactionMapperMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@Service
public class TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private RepositoryContext repositoryContext;

	public List<TransactionDto> getTransactions(final int type) {
		try {
			List<?> transactions = Collections.emptyList();
			final TransactionTypeEnum typeEnum = TransactionTypeEnum.findById(type);
			if (TransactionTypeEnum.ALL.equals(typeEnum)) {
				transactions = repositoryContext.getDatabaseInstance().findAll();
			} else {
				transactions = repositoryContext.getDatabaseInstance().findByType(typeEnum.name());
			}
			return buildTransacionDtoList(transactions);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED);
		}
	}

	public List<TransactionDto> findTransactionByWeight(final int weight) {
		try {
			final List<?> transactions = repositoryContext.getDatabaseInstance().findByWeight(weight);
			return buildTransacionDtoList(transactions);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED);
		}
	}

	private List<TransactionDto> buildTransacionDtoList(final List<?> transactions) {
		if (transactions.isEmpty()) {
			return Collections.emptyList();
		}

		final List<TransactionDto> list = new ArrayList<>();
		if (transactions.get(0) instanceof TransactionMySql) {
			transactions.stream().forEach(t -> list.add(TransactionMapperMySql.INSTANCE.transactionToTransactionDto((TransactionMySql) t)));
		} else if (transactions.get(0) instanceof TransactionMongo) {
			transactions.stream().forEach(t -> list.add(TransactionMapperMongo.INSTANCE.transactionToTransactionDto((TransactionMongo) t)));
		}
		return list;
	}

	public void saveTransaction(final TransactionDto request) {
		try {
			final TransactionDao<?> transactionDao = repositoryContext.getDatabaseInstance();
			persistEntity(request, transactionDao);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED);
		}
	}

	public void updateTransaction(final String id, final TransactionDto request) {
		try {
			final TransactionDao<?> transactionDao = repositoryContext.getDatabaseInstance();
			// NoSuchElementException is thrown if transaction does not exist
			transactionDao.exists(id);
			request.setId(id);
			persistEntity(request, transactionDao);
		} catch (final NoSuchElementException nse) {
			LOGGER.error(nse.getMessage(), nse);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_UPDATED);
		}
	}

	public void deleteTransaction(final String id) {
		try {
			final TransactionDao<?> transactionDao = repositoryContext.getDatabaseInstance();
			// NoSuchElementException is thrown if transaction does not exist
			transactionDao.exists(id);
			transactionDao.delete(id);
		} catch (final NoSuchElementException nse) {
			LOGGER.error(nse.getMessage(), nse);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_DELETED);
		}
	}

	@SuppressWarnings("unchecked")
	private void persistEntity(final TransactionDto request, final TransactionDao<?> transactionDao) {
		if (RepositoryUtil.isMySql()) {
			final TransactionMySql mySqlEntity = TransactionMapperMySql.INSTANCE.transactionDtoToTransactionMysql(request);
			((TransactionDao<TransactionMySql>) transactionDao).persist(mySqlEntity);
		} else if (RepositoryUtil.isMongoDb()) {
			final TransactionMongo mongoEntity = TransactionMapperMongo.INSTANCE.transactionDtoToTransactionMongo(request);
			((TransactionDao<TransactionMongo>) transactionDao).persist(mongoEntity);
		}
	}
}
