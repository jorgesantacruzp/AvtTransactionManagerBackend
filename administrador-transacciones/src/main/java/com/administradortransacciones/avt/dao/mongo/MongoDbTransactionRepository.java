package com.administradortransacciones.avt.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

public interface MongoDbTransactionRepository extends MongoRepository<TransactionMongo, String> {

	List<TransactionMongo> findByWeight(int weight);

	List<TransactionMongo> findByTypeName(String type);

	List<TransactionMongo> findByWeightAndTypeName(int weight, String type);

}
