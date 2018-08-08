package com.administradortransacciones.avt.common.dto;

public class TransactionDto extends ApiBase {

	private String name;
	private Integer weight;
	private String createdDate;
	private String type;
	private String dataStructure;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(final Integer weight) {
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

	public String getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(final String dataStructure) {
		this.dataStructure = dataStructure;
	}

}
