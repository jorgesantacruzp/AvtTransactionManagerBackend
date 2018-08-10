package com.administradortransacciones.avt.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.unit.util.TransactionTestUtil;

public class RepositoryControllerIntegrationTest extends BaseTransactionControllerIntegrationTest {

	@Test
	public void shouldReturnChosenRepository() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
		when(mySqlTransactionRepository.findById(3L)).thenReturn(Optional.of(new TransactionMySql()));
		given()
			.header("Content-Type", "application/json")
			.body(TransactionTestUtil.getTransactionDtoSample())
			.get("/repositories")
			.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldSaveTransactionFromMongoDb() {
		RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
		given()
			.header("Content-Type", "application/json")
			.get("/repositories")
			.then().statusCode(HttpStatus.OK.value())
			.assertThat()
			.body(is(RepositoryEnum.MONGODB.name()));
	}

	@Test
	public void shouldChangeChosenRepositoryToMySql() {
		when(mongoTransactionRepository.count()).thenReturn(0L);
		given()
			.header("Content-Type", "application/json")
			.post("/repositories/MYSQL")
			.then().statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void shouldChangeChosenRepositoryToMongoDb() {
		when(mySqlTransactionRepository.count()).thenReturn(0L);
		given()
			.header("Content-Type", "application/json")
			.post("/repositories/MONGODB")
			.then().statusCode(HttpStatus.CREATED.value());
	}

}
