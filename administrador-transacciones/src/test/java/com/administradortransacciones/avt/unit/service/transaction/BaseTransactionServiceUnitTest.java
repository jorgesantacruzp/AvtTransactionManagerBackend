package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.service.TransactionService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseTransactionServiceUnitTest extends BaseSpringUnitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	protected TransactionDao transactionDao;

	@Mock
	protected RepositoryContext repositoryContext;

	@InjectMocks
	protected TransactionService transactionService;

	@Before
	public void before() {
		when(repositoryContext.getDatabaseInstance()).thenReturn(transactionDao);
	}

}
