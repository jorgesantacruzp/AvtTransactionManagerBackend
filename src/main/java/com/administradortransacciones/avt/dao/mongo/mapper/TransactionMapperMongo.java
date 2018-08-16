package com.administradortransacciones.avt.dao.mongo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.mapper.util.CurrentDate;
import com.administradortransacciones.avt.dao.mongo.mapper.util.TransactionMapperUtilMongo;
import com.administradortransacciones.avt.dao.mongo.mapper.util.TransactionTypeToStringMongo;
import com.administradortransacciones.avt.dao.mongo.model.TransactionMongo;

@Mapper(componentModel = "spring", uses = TransactionMapperUtilMongo.class)
public interface TransactionMapperMongo {

	@Mapping(source = "type", target = "type", qualifiedBy = TransactionTypeToStringMongo.class)
	@Mapping(source = "createdDate", target = "createdDate", dateFormat = "dd/MM/yyyy")
	TransactionDto entityToDto(TransactionMongo transaction);

	@Mapping(source = "type", target = "type.name")
	@Mapping(source = "dataStructure", target = "type.dataStructure")
	@Mapping(source = "createdDate", target = "createdDate", qualifiedBy = CurrentDate.class)
	TransactionMongo dtoToEntity(TransactionDto transaction);
}
