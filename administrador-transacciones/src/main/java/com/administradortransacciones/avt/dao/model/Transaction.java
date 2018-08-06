package com.administradortransacciones.avt.dao.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int weight;
	private Date createdDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private TransactionType type;

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

	public TransactionType getType() {
		return type;
	}

	public void setType(final TransactionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", name=" + name + ", weight=" + weight + ", createdDate=" + createdDate + ", type=" + type + "]";
	}

}
