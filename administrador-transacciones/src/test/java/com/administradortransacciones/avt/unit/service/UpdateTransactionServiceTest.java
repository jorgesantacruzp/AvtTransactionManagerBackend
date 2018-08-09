package com.administradortransacciones.avt.unit.service;

import static org.mockito.Mockito.doThrow;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

public class UpdateTransactionServiceTest extends BaseSpringUnitTest {

	@Test
	public void shouldUpdateTransactionInMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldUpdateTransactionInMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldThrowTransactionExceptionWhenUserNotExistsWhileUpdating() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND.getCode());

		doThrow(new NoSuchElementException()).when(transactionDao).exists("1");
		transactionService.updateTransaction("1", new TransactionDto());
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileUpdating() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_UPDATED.getCode());

		doThrow(new TransactionException()).when(transactionDao).exists("1");
		transactionService.updateTransaction("1", new TransactionDto());
	}

}
