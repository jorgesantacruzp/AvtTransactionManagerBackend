package com.administradortransacciones.avt.integration;

import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

public class DeleteTransactionControllerIntegrationTest extends BaseTransactionControllerIntegrationTest {

	@Test
	public void shouldDeleteTransactionFromMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findById(3L)).thenReturn(Optional.of(new TransactionMySql()));
		delete("/transactions/3")
			.then().statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void shouldDeleteTransactionFromMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findById("3")).thenReturn(Optional.of(new TransactionMongo()));
		delete("/transactions/3")
			.then().statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void shouldThrowNoSuchElementExceptionWhileDeleting() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findById("1")).thenReturn(Optional.of(new TransactionMongo()));
		delete("/transactions/3")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND.getCode()));
	}

	@Test
	public void shouldThrowTransactionExceptionWhileDeleting() {
		when(mongoTransactionRepository.findById("3")).thenThrow(new TransactionException());
		delete("/transactions/3")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_DELETED.getCode()));
	}

}
