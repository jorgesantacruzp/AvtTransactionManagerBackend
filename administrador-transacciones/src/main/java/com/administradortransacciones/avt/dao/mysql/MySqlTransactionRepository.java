package com.administradortransacciones.avt.dao.mysql;

import org.springframework.data.repository.CrudRepository;

import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

public interface MySqlTransactionRepository extends CrudRepository<TransactionMySql, Long> {

}
