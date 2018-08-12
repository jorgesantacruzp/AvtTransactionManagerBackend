package com.administradortransacciones.avt.unit.service.edt;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.TransactionQueue;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

public class QueueTest extends BaseSpringUnitTest {

	@Test
	public void shouldReturnHeadOfQueue() {
		final TransactionQueue binaryTree = buildQueue();
		assertThat(binaryTree.peek().getWeight(), is(210));
	}

	@Test
	public void shouldReturnAndRemoveHeadOfQueue() {
		final TransactionQueue binaryTree = buildQueue();
		assertThat(binaryTree.dequeue().getWeight(), is(210));
		assertThat(binaryTree.list(), hasSize(2));
	}

	@Test
	public void shouldReturnAndRemoveLastElementOfQueue() {
		final TransactionQueue binaryTree = buildQueue();
		binaryTree.dequeue();
		binaryTree.dequeue();
		binaryTree.dequeue();
		assertThat(binaryTree.list(), hasSize(0));
	}

	@Test
	public void shouldRemoveElementsOfQueue() {
		final TransactionQueue binaryTree = buildQueue();
		binaryTree.delete(211);
		binaryTree.delete(210);
		binaryTree.delete(212);
		assertThat(binaryTree.list(), hasSize(0));
	}

	private TransactionQueue buildQueue() {
		final TransactionQueue binaryTree = new TransactionQueue();
		binaryTree.enqueue(new TransactionDto(210));
		binaryTree.enqueue(new TransactionDto(211));
		binaryTree.enqueue(new TransactionDto(212));
		return binaryTree;
	}

}
