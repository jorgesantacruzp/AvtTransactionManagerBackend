package com.administradortransacciones.avt.integration;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.administradortransacciones.avt.common.ErrorCodesEnum;
import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.exception.TransactionException;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class GetTransactionControllerIntegrationTest extends BaseTransactionControllerIntegrationTest {

	@Test
	public void shouldGetAllTransactionsFromMySql() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findAll()).thenReturn(TransactionTestUtil.getTransactionsMySql());
		get("/transactions")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("name", hasItems("Sample transaction"));
	}

	@Test
	public void shouldGetAllTransactionsFromMongo() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findAll()).thenReturn(TransactionTestUtil.getTransactionsMongo());
		get("/transactions")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("name", hasItems("Sample transaction"));;
	}

	@Test
	public void shouldThrowTransactionExceptionWhenOccursGeneralErrorWhileGettingAllTransactions() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findAll()).thenThrow(new TransactionException());
		get("/transactions")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED.getCode()));
	}
	
	@Test
	public void shouldGetTransactionsByTypeCheckChange() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findByTypeName(TransactionTypeEnum.CHECK_CHANGE.name()))
						.thenReturn(TransactionTestUtil.getTransactionsMongo());
		get("/transactions?type=1")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("name", hasItems("Sample transaction"));
	}

	@Test
	public void shouldGetTransactionsByWeight() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findByWeight(1)).thenReturn(TransactionTestUtil.getTransactionsMongo());
		get("/transactions/1")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("name", hasItems("Sample transaction"));
	}
	
	@Test
	public void shouldThrowTransactionExceptionWhileGettingTransactionsByWeight() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findByWeight(1)).thenThrow(new TransactionException());
		get("/transactions/1")
			.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.assertThat()
			.body("errorType", is(ErrorCodesEnum.ATXN_TRANSACTION_NOT_FETCHED.getCode()));
	}
	
	@Test
	public void shouldGetTransactionsByWeightAndType() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		when(mongoTransactionRepository.findByWeightAndTypeName(1, TransactionTypeEnum.CHECK_CHANGE.name()))
						.thenReturn(TransactionTestUtil.getTransactionsMongo());
		get("/transactions/1?type=1")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("name", hasItems("Sample transaction"));
	}

}
