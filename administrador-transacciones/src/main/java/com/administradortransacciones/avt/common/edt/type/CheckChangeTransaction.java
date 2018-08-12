package com.administradortransacciones.avt.common.edt.type;

import java.util.List;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.TransactionBinaryTree;
import com.administradortransacciones.avt.common.edt.TransactionQueue;

public class CheckChangeTransaction extends TransactionTypeDataStructure {

	private static CheckChangeTransaction instance;

	private CheckChangeTransaction() {
		super();
		queue = new TransactionQueue();
		binaryTree = new TransactionBinaryTree();
	}

	public static CheckChangeTransaction getInstance() {
		if (instance == null) {
			instance = new CheckChangeTransaction();
		}
		return instance;
	}

	@Override
	public void enqueueAll(final List<TransactionDto> transactions) {
		cleanQueue();
		transactions.stream().filter(t -> TransactionTypeEnum.CHECK_CHANGE.name().equals(t.getType())).forEach(t -> {
			queue.enqueue(t);
		});
	}

	@Override
	public void addAllToBinaryTree(final List<TransactionDto> transactions) {
		cleanBinaryTree();
		transactions.stream().filter(t -> TransactionTypeEnum.CHECK_CHANGE.name().equals(t.getType())).forEach(t -> {
			binaryTree.insert(t);
		});
	}

}
