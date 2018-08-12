package com.administradortransacciones.avt.unit.service.edt;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.DataStructureEnum;
import com.administradortransacciones.avt.service.DataStructureService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class EmptyDataStructureServiceTest extends BaseSpringUnitTest {

	@InjectMocks
	private DataStructureService dataStructureService;

	@Test
	public void shouldBeEmptyBinarySearchForPayrollPayment() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}

	@Test
	public void shouldBeEmptyQueueForPayrollPayment() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.PAYROLL_PAYMENT, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}

	@Test
	public void shouldBeEmptyBinarySearchForCheckChange() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}

	@Test
	public void shouldBeEmptyQueueForCheckChange() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.CHECK_CHANGE, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}

	@Test
	public void shouldBeEmptyBinarySearchForMoneyTransfer() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.BINARY_TREE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}

	@Test
	public void shouldBeEmptyQueueForMoneyTransfer() {
		final TransactionDto transactionDto = TransactionTestUtil
						.getTransactionDtoSample(TransactionTypeEnum.MONEY_TRANSFER, DataStructureEnum.QUEUE);
		dataStructureService.setNewDataStructure(transactionDto);
		dataStructureService.cleanDataStructures();
		assertThat(dataStructureService.isEmpty(transactionDto), is(true));
	}
}
