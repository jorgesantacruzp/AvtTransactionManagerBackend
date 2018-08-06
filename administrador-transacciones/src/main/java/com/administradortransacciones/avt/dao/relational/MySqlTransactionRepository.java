package com.administradortransacciones.avt.dao.relational;

import org.springframework.data.repository.CrudRepository;

import com.administradortransacciones.avt.dao.model.Transaction;

public interface MySqlTransactionRepository extends CrudRepository<Transaction, Long> {

}
