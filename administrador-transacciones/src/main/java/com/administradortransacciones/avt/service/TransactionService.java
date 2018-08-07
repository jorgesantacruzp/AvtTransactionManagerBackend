package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.mapper.TransactionMapper;
import com.administradortransacciones.avt.dao.RepositoryContext;
import com.administradortransacciones.avt.dao.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	private RepositoryContext repositoryContext;

	public List<TransactionDto> getTransactions() {
		List<TransactionDto> list = new ArrayList<>();
		List<Transaction> results = repositoryContext.getDatabaseInstance("MYSQL").findAll();
		results.stream().forEach(t -> list.add(TransactionMapper.INSTANCE.transactionToTransactionDto(t)));
		return list;
	}
}
