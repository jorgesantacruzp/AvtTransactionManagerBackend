package com.administradortransacciones.avt.unit.service.edt;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.TransactionBinaryTree;
import com.administradortransacciones.avt.unit.BaseSpringUnitTest;

public class BinarySearchTest extends BaseSpringUnitTest {

	@Test
	public void shouldTraverseBinaryTreeInOrder() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, contains(211, 212, 214));
	}

	@Test
	public void shouldTraverseBinaryTreePreOrder() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.traversePreOrder(binaryTree.getRoot(), list);
		assertThat(list, contains(212, 211, 214));
	}

	@Test
	public void shouldTraverseBinaryTreePostOrder() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.traversePostOrder(binaryTree.getRoot(), list);
		assertThat(list, contains(211, 214, 212));
	}

	@Test
	public void shouldDeleteLeftNodeWithNoChild() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.delete(211);
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, hasSize(2));
	}

	@Test
	public void shouldDeleteRightNodeWithNoChild() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.delete(214);
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, hasSize(2));
	}

	@Test
	public void shouldDeleteNodeWithLeftAndRightChild() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.delete(212);
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, hasSize(2));
	}

	@Test
	public void shouldDeleteNodeWithLeftChild() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		binaryTree.insert(new TransactionDto(210));
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.delete(211);
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, hasSize(3));
	}

	@Test
	public void shouldDeleteNodeWithRightChild() {
		final TransactionBinaryTree binaryTree = buildCompleteBinaryTree();
		binaryTree.insert(new TransactionDto(215));
		List<TransactionDto> list = new ArrayList<>();
		binaryTree.delete(214);
		binaryTree.traverseInOrder(binaryTree.getRoot(), list);
		assertThat(list, hasSize(3));
	}

	private TransactionBinaryTree buildCompleteBinaryTree() {
		final TransactionBinaryTree binaryTree = new TransactionBinaryTree();
		binaryTree.insert(new TransactionDto(212));
		binaryTree.insert(new TransactionDto(214));
		binaryTree.insert(new TransactionDto(211));
		return binaryTree;
	}

}
