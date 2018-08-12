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

	private TransactionMapperMongo transactionMapperMongo;
	private TransactionMapperMySql transactionMapperMySql;
	private RepositoryContext repositoryContext;
	private DataStructureService dataStructureService;

	@Autowired
	public void setTransactionMapperMongo(TransactionMapperMongo transactionMapperMongo) {
		this.transactionMapperMongo = transactionMapperMongo;
	}

	@Autowired
	public void setTransactionMapperMySql(TransactionMapperMySql transactionMapperMySql) {
		this.transactionMapperMySql = transactionMapperMySql;
	}

	@Autowired
	public void setRepositoryContext(RepositoryContext repositoryContext) {
		this.repositoryContext = repositoryContext;
	}

	@Autowired
	public void setDataStructureService(DataStructureService dataStructureService) {
		this.dataStructureService = dataStructureService;
	}

	public List<TransactionDto> getTransactions(final int type) {
		try {
			List<?> transactions = Collections.emptyList();
			final TransactionTypeEnum typeEnum = TransactionTypeEnum.findById(type);

			// search in memory if there are transactions
			List<TransactionDto> transactionsDto = dataStructureService.getAllTransactions(typeEnum);
			if (!transactionsDto.isEmpty()) {
				LOGGER.info("Transactions fetched from data structure");
				return transactionsDto;
			}

			// if not in memory search in database
			if (TransactionTypeEnum.ALL.equals(typeEnum)) {
				transactions = repositoryContext.getDatabaseInstance().findAll();
			} else {
				transactions = repositoryContext.getDatabaseInstance().findByType(typeEnum.name());
			}
			transactionsDto = buildTransacionDtoList(transactions);

			// save all transactions in memory
			if (TransactionTypeEnum.ALL.equals(typeEnum)) {
				dataStructureService.addTransactions(transactionsDto);
			}
			return transactionsDto;
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED);
		}
	}

	public List<TransactionDto> findTransactionByWeight(final int weight, final int type) {
		try {
			List<?> transactions = Collections.emptyList();
			final TransactionTypeEnum typeEnum = TransactionTypeEnum.findById(type);

			// search in memory if the transaction exists
			final List<TransactionDto> transactionsDto = dataStructureService.findTransaction(weight, typeEnum);
			if (!transactionsDto.isEmpty()) {
				LOGGER.info("Transactions fetched from data structure");
				return transactionsDto;
			}

			// if not in memory search in database
			if (TransactionTypeEnum.ALL.equals(typeEnum)) {
				transactions = repositoryContext.getDatabaseInstance().findByWeight(weight);
			} else {
				transactions = repositoryContext.getDatabaseInstance().findByWeightAndType(weight, typeEnum.name());
			}
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
			transactions.stream().forEach(t -> {
				list.add(transactionMapperMySql.entityToDto((TransactionMySql) t));
			});
		} else if (transactions.get(0) instanceof TransactionMongo) {
			transactions.stream().forEach(t -> {
				list.add(transactionMapperMongo.entityToDto((TransactionMongo) t));
			});
		}
		return list;
	}

	public void saveTransaction(final TransactionDto request) {
		try {
			final TransactionDao<?> transactionDao = repositoryContext.getDatabaseInstance();
			final TransactionDto transactionDto = persistEntity(request, transactionDao);

			// save transaction in memory
			dataStructureService.addTransaction(transactionDto);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED);
		}
	}

	public void updateTransaction(final String id, final TransactionDto request) {
		try {
			final TransactionDao<?> transactionDao = repositoryContext.getDatabaseInstance();
			// NoSuchElementException is thrown if transaction does not exist
			transactionDao.findById(id);
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
			Object transactionEntity = transactionDao.findById(id);
			transactionDao.delete(id);

			TransactionDto transactionDto = new TransactionDto();
			if (transactionEntity instanceof TransactionMySql) {
				transactionDto = transactionMapperMySql.entityToDto((TransactionMySql) transactionEntity);
			} else if (transactionEntity instanceof TransactionMongo) {
				transactionDto = transactionMapperMongo.entityToDto((TransactionMongo) transactionEntity);
			}

			// delete transaction in memory
			dataStructureService.deleteTransaction(transactionDto);
		} catch (final NoSuchElementException nse) {
			LOGGER.error(nse.getMessage(), nse);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND);
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_DELETED);
		}
	}

	@SuppressWarnings("unchecked")
	private TransactionDto persistEntity(final TransactionDto request, final TransactionDao<?> transactionDao) {
		if (RepositoryUtil.isMySql()) {
			final TransactionMySql mySqlEntity = transactionMapperMySql.dtoToEntity(request);
			TransactionMySql savedEntity = ((TransactionDao<TransactionMySql>) transactionDao).persist(mySqlEntity);
			return transactionMapperMySql.entityToDto(savedEntity);
		} else if (RepositoryUtil.isMongoDb()) {
			final TransactionMongo mongoEntity = transactionMapperMongo.dtoToEntity(request);
			TransactionMongo savedEntity = ((TransactionDao<TransactionMongo>) transactionDao).persist(mongoEntity);
			return transactionMapperMongo.entityToDto(savedEntity);
		}
		return null;
	}
}
