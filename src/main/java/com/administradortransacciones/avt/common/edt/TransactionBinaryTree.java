package com.administradortransacciones.avt.common.edt;

import java.util.List;

import com.administradortransacciones.avt.common.dto.TransactionDto;

public class TransactionBinaryTree {

	private Node root;

	public void insert(final TransactionDto data) {
		final Node newNode = new Node(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (data.getWeight() < current.data.getWeight()) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public boolean delete(final int weight) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (current.data.getWeight() != weight) {
			parent = current;
			if (current.data.getWeight() > weight) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				return false;
			}
		}
		if (current.left == null && current.right == null) {
			// if node to be deleted has no children
			if (current == root) {
				root = null;
			}
			if (isLeftChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		} else if (current.right == null) {
			// if node to be deleted has only left child
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.left == null) {
			// if node to be deleted has only right child
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.left != null && current.right != null) {
			// if node to be deleted has left and right child
			final Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}

	private Node getSuccessor(final Node deleteNode) {
		Node successsor = new Node(null);
		Node successsorParent = new Node(null);
		Node current = deleteNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		if (successsor != deleteNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleteNode.right;
		}
		return successsor;
	}

	public TransactionDto search(final int weight) {
		Node current = root;
		while (current != null) {
			if (current.data.getWeight() == weight) {
				return current.data;
			} else if (current.data.getWeight() > weight) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void traverseInOrder(final Node node, final List<TransactionDto> list) {
		if (node != null) {
			traverseInOrder(node.left, list);
			list.add(node.data);
			traverseInOrder(node.right, list);
		}
	}

	public void traversePreOrder(final Node node, final List<TransactionDto> list) {
		if (node != null) {
			list.add(node.data);
			traversePreOrder(node.left, list);
			traversePreOrder(node.right, list);
		}
	}

	public void traversePostOrder(final Node node, final List<TransactionDto> list) {
		if (node != null) {
			traversePostOrder(node.left, list);
			traversePostOrder(node.right, list);
			list.add(node.data);
		}
	}

	public Node getRoot() {
		return this.root;
	}

	private class Node {
		TransactionDto data;
		Node left;
		Node right;

		Node(final TransactionDto data) {
			this.data = data;
			right = null;
			left = null;
		}
	}

}
