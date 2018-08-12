package com.administradortransacciones.avt.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class TransactionDto {

	private String id;
	private String name;
	private Integer weight;
	private String createdDate;
	private String type;
	private String dataStructure;

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

	@Override
	public boolean equals(final Object obj) {
		return true;
	}

}
