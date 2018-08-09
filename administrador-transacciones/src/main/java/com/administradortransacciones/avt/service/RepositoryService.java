package com.administradortransacciones.avt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.RepositoryEnum;
import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.dao.RepositoryContext;

@Service
public class RepositoryService {

	@Autowired
	private RepositoryContext repositoryContext;

	public void saveRepositoryInMemory(final String repository) {
		if (RepositoryEnum.MYSQL.name().equals(repository)) {
			int transactionsInMongo = repositoryContext.getDatabaseInstance(RepositoryEnum.MONGODB.name()).count();
			if (transactionsInMongo == 0) {
				RepositoryUtil.setChosenRepository(RepositoryEnum.MYSQL.name());
			}
		} else if (RepositoryEnum.MONGODB.name().equals(repository)) {
			int transactionsInMySql = repositoryContext.getDatabaseInstance(RepositoryEnum.MYSQL.name()).count();
			if (transactionsInMySql == 0) {
				RepositoryUtil.setChosenRepository(RepositoryEnum.MONGODB.name());
			}
		}
	}

}
