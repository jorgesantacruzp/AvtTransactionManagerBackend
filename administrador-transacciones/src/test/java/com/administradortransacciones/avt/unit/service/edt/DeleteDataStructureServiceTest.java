package com.administradortransacciones.avt.unit.service.edt;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.DataStructureEnum;
import com.administradortransacciones.avt.service.DataStructureService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class DeleteDataStructureServiceTest extends BaseSpringUnitTest {

	@InjectMocks
	private DataStructureService dataStructureService;

	@Test
	public void shouldDeleteMoneyTransferTransactionsInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

	@Test
	public void shouldDeleteCheckChangeTransactionsInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

	@Test
	public void shouldDeleteCheckChangeTransactionsInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

	@Test
	public void shouldDeleteMoneyTransferTransactionsInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

	@Test
	public void shouldDeletePayrollPaymentTransactionsInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

	@Test
	public void shouldDeletePayrollPaymentTransactionsInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
		dataStructureService.deleteTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.getAllTransactions(TransactionTypeEnum.ALL);
		assertThat(transactions, hasSize(2));
	}

}
