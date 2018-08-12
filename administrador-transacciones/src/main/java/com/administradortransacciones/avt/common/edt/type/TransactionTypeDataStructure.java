package com.administradortransacciones.avt.common.edt.type;

import java.util.ArrayList;
import java.util.List;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.DataStructureEnum;
import com.administradortransacciones.avt.common.edt.TransactionBinaryTree;
import com.administradortransacciones.avt.common.edt.TransactionQueue;

public abstract class TransactionTypeDataStructure {

	protected TransactionQueue queue;
	protected TransactionBinaryTree binaryTree;

	protected boolean isBinaryTree = true;

	abstract void enqueueAll(final List<TransactionDto> transactions);

	abstract void addAllToBinaryTree(final List<TransactionDto> transactions);

	public void addTransaction(final TransactionDto transaction) {
		if (isBinaryTree) {
			binaryTree.insert(transaction);
		} else {
			queue.enqueue(transaction);
		}
	}

	public void addTransactions(final List<TransactionDto> transactions) {
		if (isBinaryTree) {
			addAllToBinaryTree(transactions);
		} else {
			enqueueAll(transactions);
		}
	}

	public List<TransactionDto> find(final int weight) {
		List<TransactionDto> list = new ArrayList<>();
		if (isBinaryTree) {
			TransactionDto transactionDto = binaryTree.search(weight);
			if (transactionDto != null) {
				list.add(transactionDto);
			}
		} else {
			TransactionDto transactionDto = queue.search(weight);
			if (transactionDto != null) {
				list.add(transactionDto);
			}
		}
		return list;
	}

	public void delete(final int weight) {
		if (isBinaryTree) {
			binaryTree.delete(weight);
		} else {
			queue.delete(weight);
		}
	}

	public List<TransactionDto> list() {
		List<TransactionDto> list = new ArrayList<>();
		if (isBinaryTree) {
			binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		} else {
			list = queue.list();
		}
		return list;
	}

	protected void cleanQueue() {
		queue = new TransactionQueue();
	}

	protected void cleanBinaryTree() {
		binaryTree = new TransactionBinaryTree();
	}

	public void cleanDataStructure() {
		if (isBinaryTree) {
			cleanBinaryTree();
		} else {
			cleanQueue();
		}
	}

	public void setBinaryTree(final String dataStructure) {
		this.isBinaryTree = DataStructureEnum.BINARY_TREE.name().equals(dataStructure);
	}

}
