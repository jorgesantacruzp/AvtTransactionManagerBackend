package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.dao.mongo.mapper.TransactionMapperMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.mapper.TransactionMapperMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.service.TransactionService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

@SuppressWarnings("unchecked")
public abstract class BaseTransactionServiceUnitTest extends BaseSpringUnitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	protected TransactionService transactionService;

	@Mock
	private TransactionMapperMongo transactionMapperMongo;

	@Mock
	private TransactionMapperMySql transactionMapperMySql;

	@Before
	public void before() {
		when(transactionMapperMySql.dtoToEntity(new TransactionDto())).thenReturn(new TransactionMySql());
		when(transactionMapperMySql.entityToDto(new TransactionMySql())).thenReturn(new TransactionDto());
		when(transactionMapperMongo.dtoToEntity(new TransactionDto())).thenReturn(new TransactionMongo());
		when(transactionMapperMongo.entityToDto(new TransactionMongo())).thenReturn(new TransactionDto());
		when(repositoryContext.getDatabaseInstance()).thenReturn(transactionDao);
	}

}
