package com.administradortransacciones.avt.unit;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.TransactionDao;
import com.administradortransacciones.avt.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseSpringUnitTest {

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
