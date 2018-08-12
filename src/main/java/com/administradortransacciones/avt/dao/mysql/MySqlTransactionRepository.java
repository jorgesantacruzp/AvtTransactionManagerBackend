package com.administradortransacciones.avt.dao.mysql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

public interface MySqlTransactionRepository extends CrudRepository<TransactionMySql, Long> {

	List<TransactionMySql> findByWeight(int weight);

	List<TransactionMySql> findByType(TransactionTypeMySql type);
	
	List<TransactionMySql> findByWeightAndType(int weight, TransactionTypeMySql type);

}
