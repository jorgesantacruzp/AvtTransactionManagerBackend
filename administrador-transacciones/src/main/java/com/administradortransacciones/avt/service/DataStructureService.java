package com.administradortransacciones.avt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.administradortransacciones.avt.common.TransactionTypeEnum;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.common.edt.type.CheckChangeTransaction;
import com.administradortransacciones.avt.common.edt.type.MoneyTransferTransaction;
import com.administradortransacciones.avt.common.edt.type.PayrollPaymentTransaction;

@Service
public class DataStructureService {

	public void addTransaction(final TransactionDto transaction) {
		if (TransactionTypeEnum.CHECK_CHANGE.name().equals(transaction.getType())) {
			CheckChangeTransaction.getInstance().addTransaction(transaction);
		} else if (TransactionTypeEnum.MONEY_TRANSFER.name().equals(transaction.getType())) {
			MoneyTransferTransaction.getInstance().addTransaction(transaction);
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(transaction.getType())) {
			PayrollPaymentTransaction.getInstance().addTransaction(transaction);
		}
	}

	public void addTransactions(final List<TransactionDto> transactions) {
		CheckChangeTransaction.getInstance().addTransactions(transactions);
		MoneyTransferTransaction.getInstance().addTransactions(transactions);
		PayrollPaymentTransaction.getInstance().addTransactions(transactions);
	}

	public void deleteTransaction(final TransactionDto transaction) {
		if (TransactionTypeEnum.CHECK_CHANGE.name().equals(transaction.getType())) {
			CheckChangeTransaction.getInstance().delete(transaction.getWeight());
		} else if (TransactionTypeEnum.MONEY_TRANSFER.name().equals(transaction.getType())) {
			MoneyTransferTransaction.getInstance().delete(transaction.getWeight());
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(transaction.getType())) {
			PayrollPaymentTransaction.getInstance().delete(transaction.getWeight());
		}
	}

	public List<TransactionDto> getAllTransactions(final TransactionTypeEnum typeEnum) {
		List<TransactionDto> list = new ArrayList<>();
		if (TransactionTypeEnum.CHECK_CHANGE.equals(typeEnum)) {
			list.addAll(CheckChangeTransaction.getInstance().list());
		} else if (TransactionTypeEnum.MONEY_TRANSFER.equals(typeEnum)) {
			list.addAll(MoneyTransferTransaction.getInstance().list());
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.equals(typeEnum)) {
			list.addAll(PayrollPaymentTransaction.getInstance().list());
		} else {
			list.addAll(CheckChangeTransaction.getInstance().list());
			list.addAll(MoneyTransferTransaction.getInstance().list());
			list.addAll(PayrollPaymentTransaction.getInstance().list());
		}
		return list;
	}

	public void cleanDataStructures() {
		CheckChangeTransaction.getInstance().cleanDataStructure();
		MoneyTransferTransaction.getInstance().cleanDataStructure();
		PayrollPaymentTransaction.getInstance().cleanDataStructure();
	}

	public List<TransactionDto> findTransaction(final int weight, final TransactionTypeEnum typeEnum) {
		List<TransactionDto> list = new ArrayList<>();
		if (TransactionTypeEnum.CHECK_CHANGE.equals(typeEnum)) {
			list.addAll(CheckChangeTransaction.getInstance().find(weight));
		} else if (TransactionTypeEnum.MONEY_TRANSFER.equals(typeEnum)) {
			list.addAll(MoneyTransferTransaction.getInstance().find(weight));
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.equals(typeEnum)) {
			list.addAll(PayrollPaymentTransaction.getInstance().find(weight));
		} else {
			list.addAll(CheckChangeTransaction.getInstance().find(weight));
			list.addAll(MoneyTransferTransaction.getInstance().find(weight));
			list.addAll(PayrollPaymentTransaction.getInstance().find(weight));
		}
		return list;
	}
}
