package com.administradortransacciones.avt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administradortransacciones.avt.common.dto.ApiBase;
import com.administradortransacciones.avt.common.dto.TransactionDto;
import com.administradortransacciones.avt.service.TransactionService;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/v1/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactions(
					@RequestParam(value = "type", defaultValue = "-1") final int type) {
		final List<TransactionDto> response = transactionService.getTransactions(type);
		return new ResponseEntity<List<TransactionDto>>(response, HttpStatus.OK);
	}

	@GetMapping("/v1/transactions/{weight}")
	public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable(required = false) final int weight,
					@RequestParam(value = "type", defaultValue = "-1") final int type) {
		final List<TransactionDto> response = transactionService.findTransactionByWeight(weight, type);
		return new ResponseEntity<List<TransactionDto>>(response, HttpStatus.OK);
	}

	@PostMapping("/v1/transactions")
	public ResponseEntity<ApiBase> saveTransaction(@RequestBody final TransactionDto request) {
		transactionService.saveTransaction(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/v1/transactions/{id}")
	public ResponseEntity<ApiBase> updateTransaction(@PathVariable final String id,
					@RequestBody final TransactionDto request) {
		transactionService.updateTransaction(id, request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/v1/transactions")
	public ResponseEntity<ApiBase> deleteTransactionsInMemory() {
		transactionService.deleteTransactionsInMemory();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/v1/transactions/{id}")
	public ResponseEntity<ApiBase> deleteTransaction(@PathVariable final String id) {
		transactionService.deleteTransaction(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
