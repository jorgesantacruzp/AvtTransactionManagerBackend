package com.administradortransacciones.avt.integration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.administradortransacciones.avt.dao.mongo.MongoDbTransactionDaoImpl;
import com.administradortransacciones.avt.dao.mongo.MongoDbTransactionRepository;
import com.administradortransacciones.avt.dao.mysql.MySqlTransactionDaoImpl;
import com.administradortransacciones.avt.dao.mysql.MySqlTransactionRepository;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTransactionControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Mock
	protected MySqlTransactionRepository mySqlTransactionRepository;

	@Mock
	protected MongoDbTransactionRepository mongoTransactionRepository;

	@Autowired
	private MySqlTransactionDaoImpl mySqlTransactionDaoImpl;

	@Autowired
	private MongoDbTransactionDaoImpl mongoTransactionDaoImpl;

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/v1";
		RestAssured.port = port;
		mySqlTransactionDaoImpl.setMysqlTransactionRepository(mySqlTransactionRepository);
		mongoTransactionDaoImpl.setMongoTransactionRepository(mongoTransactionRepository);
	}

}
