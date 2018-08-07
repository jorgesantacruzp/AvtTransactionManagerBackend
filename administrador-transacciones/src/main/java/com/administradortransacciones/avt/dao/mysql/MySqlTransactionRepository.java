package com.administradortransacciones.avt.dao.mysql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

public interface MySqlTransactionRepository extends CrudRepository<TransactionMySql, Long> {

	List<TransactionMySql> findByWeight(int weight);

}
