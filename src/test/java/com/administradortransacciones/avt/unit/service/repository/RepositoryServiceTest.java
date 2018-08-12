package com.administradortransacciones.avt.unit.service.repository;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.service.RepositoryService;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

@SuppressWarnings("unchecked")
public class RepositoryServiceTest extends BaseSpringUnitTest {

	@InjectMocks
	private RepositoryService repositoryService;

	@Test
	public void shouldSaveMySqlRepositoryInMemory() {
		when(repositoryContext.getDatabaseInstance(RepositoryEnum.MONGODB.name())).thenReturn(transactionDao);
		when(transactionDao.count()).thenReturn(0);
		repositoryService.saveRepositoryInMemory(RepositoryEnum.MYSQL.name());
	}

	@Test
	public void shouldSaveMongoRepositoryInMemory() {
		when(repositoryContext.getDatabaseInstance(RepositoryEnum.MYSQL.name())).thenReturn(transactionDao);
		when(transactionDao.count()).thenReturn(0);
		repositoryService.saveRepositoryInMemory(RepositoryEnum.MONGODB.name());
	}

}
