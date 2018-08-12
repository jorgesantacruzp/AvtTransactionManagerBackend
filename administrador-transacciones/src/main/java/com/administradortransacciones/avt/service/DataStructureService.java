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
		if (TransactionTypeEnum.CHECK_CHANGE.name().equals(transaction.getName())) {
			CheckChangeTransaction.getInstance().addTransaction(transaction);
		} else if (TransactionTypeEnum.MONEY_TRANSFER.name().equals(transaction.getName())) {
			MoneyTransferTransaction.getInstance().addTransaction(transaction);
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(transaction.getName())) {
			PayrollPaymentTransaction.getInstance().addTransaction(transaction);
		}
	}

	public void addTransactions(final List<TransactionDto> transactions) {
		CheckChangeTransaction.getInstance().addTransactions(transactions);
		MoneyTransferTransaction.getInstance().addTransactions(transactions);
		PayrollPaymentTransaction.getInstance().addTransactions(transactions);
	}

	public void deleteTransaction(final TransactionDto transaction) {
		if (TransactionTypeEnum.CHECK_CHANGE.name().equals(transaction.getName())) {
			CheckChangeTransaction.getInstance().delete(transaction.getWeight());
		} else if (TransactionTypeEnum.MONEY_TRANSFER.name().equals(transaction.getName())) {
			MoneyTransferTransaction.getInstance().delete(transaction.getWeight());
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.name().equals(transaction.getName())) {
			PayrollPaymentTransaction.getInstance().delete(transaction.getWeight());
		}
	}

	public List<TransactionDto> getAllTransactions() {
		List<TransactionDto> list = new ArrayList<>();
		list.addAll(CheckChangeTransaction.getInstance().list());
		list.addAll(MoneyTransferTransaction.getInstance().list());
		list.addAll(PayrollPaymentTransaction.getInstance().list());
		return list;
	}

	public void cleanDataStructures() {
		CheckChangeTransaction.getInstance().cleanDataStructure();
		MoneyTransferTransaction.getInstance().cleanDataStructure();
		PayrollPaymentTransaction.getInstance().cleanDataStructure();
	}

	public List<TransactionDto> findTransaction(final int weight, final int type) {
		List<TransactionDto> list = new ArrayList<>();
		if (TransactionTypeEnum.ALL.getId() == type) {
			list.add(CheckChangeTransaction.getInstance().find(weight));
			list.add(MoneyTransferTransaction.getInstance().find(weight));
			list.add(PayrollPaymentTransaction.getInstance().find(weight));
			return list;
		}
		if (TransactionTypeEnum.CHECK_CHANGE.equals(TransactionTypeEnum.findById(type))) {
			list.add(CheckChangeTransaction.getInstance().find(weight));
		} else if (TransactionTypeEnum.MONEY_TRANSFER.equals(TransactionTypeEnum.findById(type))) {
			list.add(MoneyTransferTransaction.getInstance().find(weight));
		} else if (TransactionTypeEnum.PAYROLL_PAYMENT.equals(TransactionTypeEnum.findById(type))) {
			list.add(PayrollPaymentTransaction.getInstance().find(weight));
		}
		return list;
	}
}
