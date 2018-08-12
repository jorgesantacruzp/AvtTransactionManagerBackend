package com.administradortransacciones.avt.common.edt.type;

import java.util.List;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.TransactionBinaryTree;
import com.administradortransacciones.avt.common.edt.TransactionQueue;

public class PayrollPaymentTransaction extends TransactionTypeDataStructure {

	private static PayrollPaymentTransaction instance;

	private PayrollPaymentTransaction() {
		super();
		queue = new TransactionQueue();
		binaryTree = new TransactionBinaryTree();
	}

	public static PayrollPaymentTransaction getInstance() {
		if (instance == null) {
			instance = new PayrollPaymentTransaction();
		}
		return instance;
	}

	@Override
	public void enqueueAll(final List<TransactionDto> transactions) {
		cleanQueue();
		transactions.stream().filter(t -> TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(t.getType())).forEach(t -> {
			queue.enqueue(t);
		});
	}

	@Override
	public void addAllToBinaryTree(final List<TransactionDto> transactions) {
		cleanBinaryTree();
		transactions.stream().filter(t -> TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(t.getType())).forEach(t -> {
			binaryTree.insert(t);
		});
	}

}
