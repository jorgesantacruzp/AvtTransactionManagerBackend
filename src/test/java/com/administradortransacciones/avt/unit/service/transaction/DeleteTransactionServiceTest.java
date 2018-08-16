package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

public class DeleteTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Test
	public void shouldDeleteTransactionInMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(transactionDao.findById("1")).thenReturn(new TransactionMySql());
		transactionService.deleteTransaction("1");
	}

	@Test
	public void shouldDeleteTransactionInMongo() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(transactionDao.findById("1")).thenReturn(new TransactionMongo());
		transactionService.deleteTransaction("1");
	}

	@Test
	public void shouldThrowTransactionExceptionWhenUserNotExistsWhileDeleting() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND.getCode());

		doThrow(new NoSuchElementException()).when(transactionDao).findById("1");
		transactionService.deleteTransaction("1");
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileDeleting() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_DELETED.getCode());

		doThrow(new TransactionException(ErrorCodesEnum.ATXN_TRANSACTION_NOT_DELETED)).when(transactionDao).delete("1");
		transactionService.deleteTransaction("1");
	}

	@Test
	public void shouldDeleteTransactionsInMemory() {
		transactionService.deleteTransactionsInMemory();
	}

}
