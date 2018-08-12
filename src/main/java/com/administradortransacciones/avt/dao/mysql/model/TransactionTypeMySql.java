package com.administradortransacciones.avt.dao.mysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.administradortransacciones.avt.common.TransactionTypeEnum;

@Entity(name = "transactionType")
public class TransactionTypeMySql {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String dataStructure;

	public TransactionTypeMySql() {
		super();
	}
	public TransactionTypeMySql(final String name) {
		this();
		this.id = Long.valueOf(TransactionTypeEnum.findByName(name).getId());
		this.name = name;
	}

	public TransactionTypeMySql(final String name, final String dataStructure) {
		this(name);
		this.dataStructure = dataStructure;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(final String dataStructure) {
		this.dataStructure = dataStructure;
	}

}
