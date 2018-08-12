package com.administradortransacciones.avt.common.edt;

import java.util.ArrayList;
import java.util.List;

import com.administradortransacciones.avt.common.dto.TransactionDto;

public class TransactionQueue {

	private Node root;
	private Node last;

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Inserts the specified element into this queue
	 * 
	 * @param data
	 */
	public void enqueue(final TransactionDto data) {
		if (data == null) {
			return;
		}
		final Node newNode = new Node();
		newNode.data = data;
		newNode.next = null;
		if (isEmpty()) {
			root = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
	}

	/**
	 * Retrieves and remove the head of this queue, or returns {@code null} if
	 * this queue is empty.
	 * 
	 * @return the head of this queue, or {@code null} if this queue is empty
	 */
	public TransactionDto dequeue() {
		if (!isEmpty()) {
			final TransactionDto data = root.data;
			if (root == last) {
				root = null;
				last = null;
			} else {
				root = root.next;
			}
			return data;
		}
		return null;
	}

	public void delete(final int weight) {
		final TransactionQueue newQueue = new TransactionQueue();
		Node tempRoot = root;
		while (tempRoot != null) {
			if (tempRoot.data.getWeight() == weight) {
				if (root == last) {
					root = null;
					last = null;
					break;
				} else {
					tempRoot = tempRoot.next;
					continue;
				}
			}
			newQueue.enqueue(tempRoot.data);
			tempRoot = tempRoot.next;
		}
		root = newQueue.root;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, or returns
	 * {@code null} if this queue is empty.
	 * 
	 * @return the head of this queue, or {@code null} if this queue is empty
	 */
	public TransactionDto peek() {
		return isEmpty() ? null : root.data;
	}

	public TransactionDto search(final int weight) {
		Node reco = root;
		while (reco != null) {
			if (reco.data.getWeight() == weight) {
				return reco.data;
			}
			reco = reco.next;
		}
		return null;
	}

	public List<TransactionDto> list() {
		final List<TransactionDto> list = new ArrayList<>();
		Node reco = root;
		while (reco != null) {
			list.add(reco.data);
			reco = reco.next;
		}
		return list;
	}

	private class Node {
		TransactionDto data;
		Node next;
	}
}
