package com.administradortransacciones.avt.common.dto;

public class TransactionDto {

	private String name;
	private int weight;
	private String createdDate;
	private String type;

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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final String createdDate) {
		this.createdDate = createdDate;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
