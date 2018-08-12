package com.administradortransacciones.avt.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.service.TransactionService;
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class SaveTransactionControllerIntegrationTest extends BaseTransactionControllerIntegrationTest {

	@Mock
	private RepositoryContext repositoryContextMock;

	@Autowired
	private RepositoryContext repositoryContext;
	
	@Autowired
	private TransactionService transactionService;

	@Test
	public void shouldSaveTransactionFromMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findById(3L)).thenReturn(Optional.of(new TransactionMySql()));
		when(mySqlTransactionRepository.save(new TransactionMySql())).thenReturn(new TransactionMySql());
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.post("/transactions")
			.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldSaveTransactionFromMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findById("3")).thenReturn(Optional.of(new TransactionMongo()));
		when(mongoTransactionRepository.save(new TransactionMongo())).thenReturn(new TransactionMongo());
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.post("/transactions")
			.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldThrowTransactionExceptionWhileSaving() {
		transactionService.setRepositoryContext(repositoryContextMock);
		when(repositoryContextMock.getDatabaseInstance()).thenThrow(new TransactionException());
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.post("/transactions")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_SAVED.getCode()));
		transactionService.setRepositoryContext(repositoryContext);
	}

}
