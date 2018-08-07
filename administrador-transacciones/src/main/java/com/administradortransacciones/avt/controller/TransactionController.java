package com.administradortransacciones.avt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/v1/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactions(
					@RequestParam(value = "type", defaultValue = "-1") final int type,
					@RequestParam(value = "repository", defaultValue = "MYSQL") final String repository) {
		return new ResponseEntity<List<TransactionDto>>(transactionService.getTransactions(repository, type), HttpStatus.OK);
	}

	@GetMapping("/v1/transactions/{weight}")
	public ResponseEntity<List<TransactionDto>> getTransaction(
					@PathVariable(required = false) final int weight, 
					@RequestParam(value = "type", defaultValue = "-1") final String type,
					@RequestParam(value = "repository", defaultValue = "MYSQL") final String repository) {
		return new ResponseEntity<List<TransactionDto>>(transactionService.findTransactionByWeight(repository, weight, type), HttpStatus.OK);
	}

	@PostMapping("/v1/transactions")
	public ResponseEntity<String> saveTransaction() {
		return new ResponseEntity<String>("save", HttpStatus.OK);
	}

	@PutMapping("/v1/transactions")
	public ResponseEntity<String> updateTransaction() {
		return new ResponseEntity<String>("update", HttpStatus.OK);
	}

	@DeleteMapping("/v1/transactions")
	public ResponseEntity<String> deleteTransaction(@RequestParam(value = "peso", defaultValue = "0") final int peso) {
		return new ResponseEntity<String>("delete " + peso, HttpStatus.OK);
	}
}
