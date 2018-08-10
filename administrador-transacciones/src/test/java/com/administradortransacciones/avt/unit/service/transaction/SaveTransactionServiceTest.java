package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;

public class SaveTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Test
	public void shouldSaveTransactionInMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		transactionService.saveTransaction(new TransactionDto());
	}

	@Test
	public void shouldSaveTransactionInMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		transactionService.saveTransaction(new TransactionDto());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileSaving() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED.getCode());

		doThrow(new TransactionException()).when(transactionDao).persist(any());
		transactionService.saveTransaction(new TransactionDto());
	}

}
