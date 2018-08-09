package com.administradortransacciones.avt.unit;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.TransactionDao;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@SuppressWarnings("rawtypes")
public abstract class BaseSpringUnitTest {

	@Mock
	protected TransactionDao transactionDao;

	@Mock
	protected RepositoryContext repositoryContext;

}
