package com.administradortransacciones.avt.unit.service.transaction;

import static com.administradortransacciones.avt.unit.util.TransactionTestUtil.getTransactionMongoSample;
import static com.administradortransacciones.avt.unit.util.TransactionTestUtil.getTransactionMySqlSample;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;

public class GetTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Test
	public void shouldGetAllTransactionsFromMySql() {
		when(transactionDao.findAll()).thenReturn(Arrays.asList(getTransactionMySqlSample()));
		List<?> transactions = transactionService.getTransactions(TransactionTypeEnum.ALL.getId());
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldGetAllTransactionsFromMongoDb() {
		when(transactionDao.findAll()).thenReturn(Arrays.asList(getTransactionMongoSample()));
		List<?> transactions = transactionService.getTransactions(TransactionTypeEnum.ALL.getId());
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldGetEmptyList() {
		when(transactionDao.findAll()).thenReturn(Collections.emptyList());
		List<?> transactions = transactionService.getTransactions(TransactionTypeEnum.ALL.getId());
		assertThat(transactions, hasSize(0));
	}

	@Test
	public void shouldGetTransactionsByType() {
		TransactionTypeEnum transactionType = TransactionTypeEnum.CHECK_CHANGE;
		when(transactionDao.findByType(transactionType.name())).thenReturn(Arrays.asList(getTransactionMongoSample()));
		List<?> transactions = transactionService.getTransactions(transactionType.getId());
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileGettingTransactions() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED.getCode());

		doThrow(new TransactionException()).when(transactionDao).findAll();
		transactionService.getTransactions(TransactionTypeEnum.ALL.getId());
	}

	@Test
	public void shouldGetTransactionsByWeight() {
		when(transactionDao.findByWeight(1)).thenReturn(Arrays.asList(getTransactionMySqlSample()));
		List<?> transactions = transactionService.findTransactionByWeight(1);
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileGettingTransactionsByWeight() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED.getCode());

		doThrow(new TransactionException()).when(transactionDao).findByWeight(1);
		transactionService.findTransactionByWeight(1);
	}

}
