package com.administradortransacciones.avt.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.mapper.util.TransactionMapperUtil;
import com.administradortransacciones.avt.common.mapper.util.TransactionTypeToString;
import com.administradortransacciones.avt.dao.model.Transaction;

@Mapper(uses = TransactionMapperUtil.class)
public interface TransactionMapper {

	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

	@Mappings({ 
		@Mapping(source = "type", target = "type", qualifiedBy = TransactionTypeToString.class), 
		@Mapping(source = "createdDate", target = "createdDate", dateFormat = "dd/MM/yyyy") 
	})
	TransactionDto transactionToTransactionDto(Transaction txn);
}
