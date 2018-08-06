package com.administradortransacciones.avt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administradortransacciones.avt.dao.model.Transaction;
import com.administradortransacciones.avt.service.TransactionService;

@RestController("/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(value = "peso", defaultValue = "0") int peso) {
		return new ResponseEntity<List<Transaction>>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> saveTransaction() {
		return new ResponseEntity<String>("save", HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateTransaction() {
		return new ResponseEntity<String>("update", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteTransaction(@RequestParam(value = "peso", defaultValue = "0") int peso) {
		return new ResponseEntity<String>("delete " + peso, HttpStatus.OK);
	}
}
