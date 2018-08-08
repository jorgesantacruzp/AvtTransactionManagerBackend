package com.administradortransacciones.avt.dao.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.mapper.util.CurrentDate;
import com.administradortransacciones.avt.dao.mysql.mapper.util.StringToTransactionTypeMySql;
import com.administradortransacciones.avt.dao.mysql.mapper.util.TransactionMapperUtilMySql;
import com.administradortransacciones.avt.dao.mysql.mapper.util.TransactionTypeToStringMySql;
import com.administradortransacciones.avt.dao.mysql.model.TransactionMySql;

@Mapper(uses = TransactionMapperUtilMySql.class)
public interface TransactionMapperMySql {

	TransactionMapperMySql INSTANCE = Mappers.getMapper(TransactionMapperMySql.class);

	@Mappings({ 
		@Mapping(source = "type", target = "type", qualifiedBy = TransactionTypeToStringMySql.class),
		@Mapping(source = "createdDate", target = "createdDate", dateFormat = "dd/MM/yyyy")
	})
	TransactionDto transactionToTransactionDto(TransactionMySql transaction);
	
	@Mappings({ 
		@Mapping(source = "type", target = "type.id", qualifiedBy = StringToTransactionTypeMySql.class),
		@Mapping(source = "createdDate", target = "createdDate", qualifiedBy = CurrentDate.class)
	})
	TransactionMySql transactionDtoToTransactionMysql(TransactionDto transaction);
}
