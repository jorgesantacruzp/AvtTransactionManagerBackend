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

public class ReturnDataStructureServiceTest extends BaseSpringUnitTest {

	@InjectMocks
	private DataStructureService dataStructureService;

	@Test
	public void shouldReturnCheckChangeTransactionInBinaryTree() {
		dataStructureService.cleanDataStructures();
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.BINARY_TREE);
		dataStructureService.addTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnMoneyTransferTransactionInBinaryTree() {
		dataStructureService.cleanDataStructures();
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.BINARY_TREE);
		dataStructureService.addTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnPayrollPaymentTransactionInBinaryTree() {
		dataStructureService.cleanDataStructures();
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.BINARY_TREE);
		dataStructureService.addTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnCheckChangeTransactionInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnMoneyTransferTransactionInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	private void setDataStructureAndAddTransaction(final TransactionDto transactionDto) {
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransaction(transactionDto);
	}

	@Test
	public void shouldReturnPayrollPaymentTransactionInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransaction(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllTransactionsByWeightAndTypeInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil.getTransactionDtoSample(TransactionTypeEnum.ALL,
						DataStructureEnum.BINARY_TREE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(3));
	}

	@Test
	public void shouldReturnAllTransactionsByWeightAndTypeInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil.getTransactionDtoSample(TransactionTypeEnum.ALL,
						DataStructureEnum.QUEUE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService.findTransaction(transactionDto.getWeight(),
						TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(3));
	}

	@Test
	public void shouldReturnAllTransactionsByTypeInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil.getTransactionDtoSample(TransactionTypeEnum.ALL,
						DataStructureEnum.BINARY_TREE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(3));
	}

	@Test
	public void shouldReturnAllTransactionsByTypeInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil.getTransactionDtoSample(TransactionTypeEnum.ALL,
						DataStructureEnum.QUEUE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(3));
	}

	@Test
	public void shouldReturnAllCheckChangeTransactionsByTypeInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.BINARY_TREE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllCheckChangeTransactionsByTypeInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllMoneyTransferTransactionsByTypeInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.BINARY_TREE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllMoneyTransferTransactionsByTypeInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllPayrollPaymentTransactionsByTypeInBinaryTree() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.BINARY_TREE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	@Test
	public void shouldReturnAllPayrollPaymentTransactionsByTypeInQueue() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.QUEUE);
		setDataStructureAndAddTransactions(transactionDto);
		final List<TransactionDto> transactions = dataStructureService
						.getAllTransactions(TransactionTypeEnum.findByName(transactionDto.getType()));
		assertThat(transactions, hasSize(1));
	}

	private void setDataStructureAndAddTransactions(final TransactionDto transactionDto) {
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		dataStructureService.addTransactions(TransactionTestUtil.getTransactionsDto());
	}

}
