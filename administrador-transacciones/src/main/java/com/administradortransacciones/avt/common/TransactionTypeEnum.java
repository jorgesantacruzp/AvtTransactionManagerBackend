package com.administradortransacciones.avt.common;

import java.util.Arrays;
import java.util.stream.Stream;

public enum TransactionTypeEnum {

	CHECK_CHANGE(1), MONEY_TRANSFER(2), PAYROLL_PAYMENT(3), ALL(-1);

	private int id;

	private TransactionTypeEnum(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static TransactionTypeEnum findById(final int id) {
		Stream<TransactionTypeEnum> stream1 = Arrays.stream(TransactionTypeEnum.values());
		return stream1.filter(t -> t.getId() == id).findFirst().orElse(TransactionTypeEnum.ALL);
	}

}
