package com.administradortransacciones.avt.dao.mysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.administradortransacciones.avt.dao.mysql.model.TransactionTypeMySql;

public interface MySqlTransactionTypeRepository extends CrudRepository<TransactionTypeMySql, Long> {

	@Transactional
	@Modifying
	@Query("update transactionType t set t.dataStructure = ?1 where t.id = ?2")
	void updateDataStructure(String dataStructure, Long id);
}
