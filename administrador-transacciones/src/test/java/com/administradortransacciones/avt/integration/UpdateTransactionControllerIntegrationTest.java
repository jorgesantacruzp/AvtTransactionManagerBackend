package com.administradortransacciones.avt.integration;

import static io.restassured.RestAssured.given;
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
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class UpdateTransactionControllerIntegrationTest extends BaseTransactionControllerIntegrationTest {

	@Test
	public void shouldUpdateTransactionFromMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findById(3L)).thenReturn(Optional.of(new TransactionMySql()));
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.put("/transactions/3")
			.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldUpdateTransactionFromMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findById("3")).thenReturn(Optional.of(new TransactionMongo()));
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.put("/transactions/3")
			.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldThrowNoSuchElementExceptionWhileUpdating() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findById("1")).thenReturn(Optional.of(new TransactionMongo()));
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.put("/transactions/3")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FOUND.getCode()));
	}

	@Test
	public void shouldThrowTransactionExceptionWhileUpdating() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findById(3L)).thenThrow(new TransactionException());
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.put("/transactions/3")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_UPDATED.getCode()));
	}

}
