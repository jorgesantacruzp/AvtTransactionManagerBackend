package com.administradortransacciones.avt.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

public interface MongoDbTransactionRepository extends MongoRepository<TransactionMongo, Long> {

}
