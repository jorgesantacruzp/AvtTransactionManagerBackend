package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import com.administradortransacciones.avt.service.TransactionService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

@SuppressWarnings("unchecked")
public abstract class BaseTransactionServiceUnitTest extends BaseSpringUnitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	protected TransactionService transactionService;

	@Before
	public void before() {
		when(repositoryContext.getDatabaseInstance()).thenReturn(transactionDao);
	}

}
