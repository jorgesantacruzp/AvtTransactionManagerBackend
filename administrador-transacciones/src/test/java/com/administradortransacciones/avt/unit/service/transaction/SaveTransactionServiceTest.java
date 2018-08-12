package com.administradortransacciones.avt.unit.service.transaction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.MySqlTransactionTypeRepository;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@SuppressWarnings("unchecked")
public class SaveTransactionServiceTest extends BaseTransactionServiceUnitTest {

	@Mock
	private MySqlTransactionTypeRepository mySqlTransactionTypeRepository;

	@Test
	public void shouldSaveTransactionInMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(transactionDao.persist(new TransactionMySql())).thenReturn(new TransactionMySql());
		transactionService.saveTransaction(new TransactionDto());
	}

	@Test
	public void shouldSaveTransactionInMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(transactionDao.persist(new TransactionMongo())).thenReturn(new TransactionMongo());
		transactionService.saveTransaction(new TransactionDto());
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileSaving() {
		thrown.expect(TransactionException.class);
		thrown.expectMessage(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED.getCode());

		doThrow(new TransactionException()).when(transactionDao).persist(any());
		transactionService.saveTransaction(new TransactionDto());
	}

	@Test
	public void shouldChangeDataStructureWhileSaving() {
		transactionService.setMySqlTransactionTypeRepository(mySqlTransactionTypeRepository);
		when(transactionDao.persist(new TransactionMySql())).thenReturn(new TransactionMySql());
		when(dataStructureService.isEmpty(new TransactionDto())).thenReturn(true);
		transactionService.saveTransaction(new TransactionDto());
	}

}
