package com.administradortransacciones.avt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.dao.relational.MySqlRepository;

@Service
public class RepositoryContext {

	private TransactionDao transactionDao;

	@Autowired
	private MySqlRepository mySqlRepository;

	public TransactionDao getDatabaseInstance(String repository) {
		if (RepositoryEnum.MYSQL.name().equals(repository)) {
			transactionDao = mySqlRepository;
		} else if (RepositoryEnum.MONGODB.name().equals(repository)) {

		}
		return transactionDao;
	}

}
