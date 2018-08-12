package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;

public class DeleteTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Test
	public void shouldDeleteTransaction() {
		when(transactionDao.findById("1")).thenReturn(true);
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

		doThrow(new TransactionException()).when(transactionDao).delete("1");
		transactionService.deleteTransaction("1");
	}

}
