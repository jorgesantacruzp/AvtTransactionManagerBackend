package com.administradortransacciones.avt.dao.mongo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.dao.mongo.mapper.util.TransactionMapperUtilMongo;
import com.administradortransacciones.avt.dao.mongo.mapper.util.TransactionTypeToStringMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

@Mapper(uses = TransactionMapperUtilMongo.class)
public interface TransactionMapperMongo {

	TransactionMapperMongo INSTANCE = Mappers.getMapper(TransactionMapperMongo.class);

	@Mappings({ 
		@Mapping(source = "type", target = "type", qualifiedBy = TransactionTypeToStringMongo.class), 
		@Mapping(source = "createdDate", target = "createdDate", dateFormat = "dd/MM/yyyy") 
	})
	TransactionDto transactionToTransactionDto(TransactionMongo transaction);
}
