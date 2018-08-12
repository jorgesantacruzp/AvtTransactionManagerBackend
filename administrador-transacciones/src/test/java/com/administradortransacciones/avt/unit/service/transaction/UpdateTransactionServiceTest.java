package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@SuppressWarnings("unchecked")
public class UpdateTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Test
	public void shouldUpdateTransactionInMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(transactionDao.persist(new TransactionMySql())).thenReturn(new TransactionMySql());
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldUpdateTransactionInMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(transactionDao.persist(new TransactionMongo())).thenReturn(new TransactionMongo());
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldThrowTransactionExceptionWhenUserNotExistsWhileUpdating() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND.getCode());

		doThrow(new NoSuchElementException()).when(transactionDao).findById("1");
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileUpdating() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_UPDATED.getCode());

		doThrow(new TransactionException()).when(transactionDao).findById("1");
		transactionService.updateTransaction("1", new TransactionDto());
	}

}
