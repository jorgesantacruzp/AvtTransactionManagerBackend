package com.administradortransacciones.avt.dao.mongo.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaccion")
public class TransactionMongo {

	private String name;
	private int weight;
	private Date createdDate;
	private TransactionTypeMongo type;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(final int weight) {
		this.weight = weight;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public TransactionTypeMongo getType() {
		return type;
	}

	public void setType(final TransactionTypeMongo type) {
		this.type = type;
	}

}
