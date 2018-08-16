package com.administradortransacciones.avt.dao.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction")
public class TransactionMongo {

	@Id
	private String id;
	private String name;
	private int weight;
	private Date createdDate;
	private TransactionTypeMongo type;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

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

	@Override
	public boolean equals(final Object obj) {
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
