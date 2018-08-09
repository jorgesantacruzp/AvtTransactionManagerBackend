package com.administradortransacciones.avt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/v1/transactions")
	public ResponseEntity<?> getTransactions(@RequestParam(value = "type", defaultValue = "-1") final int type) {
		final ApiBase response = transactionService.getTransactions(type);
		if (StringUtils.isEmpty(response.getMessage())) {
			return new ResponseEntity<List<TransactionDto>>(response.getTransactions(), HttpStatus.OK);
		}
		return new ResponseEntity<ApiBase>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/v1/transactions/{weight}")
	public ResponseEntity<?> getTransaction(@PathVariable(required = false) final int weight) {
		final ApiBase response = transactionService.findTransactionByWeight(weight);
		if (StringUtils.isEmpty(response.getMessage())) {
			return new ResponseEntity<List<TransactionDto>>(response.getTransactions(), HttpStatus.OK);
		}
		return new ResponseEntity<ApiBase>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/v1/transactions")
	public ResponseEntity<ApiBase> saveTransaction(@RequestBody final TransactionDto request) {
		final ApiBase response = transactionService.saveTransaction(request);
		if (StringUtils.isEmpty(response.getMessage())) {
			return new ResponseEntity<ApiBase>(HttpStatus.CREATED);
		}
		return new ResponseEntity<ApiBase>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/v1/transactions/{id}")
	public ResponseEntity<ApiBase> updateTransaction(
					@PathVariable final String id,
					@RequestBody final TransactionDto request) {
		final ApiBase response = transactionService.updateTransaction(id, request);
		if (StringUtils.isEmpty(response.getMessage())) {
			return new ResponseEntity<ApiBase>(HttpStatus.OK);
		}
		return new ResponseEntity<ApiBase>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/v1/transactions")
	public ResponseEntity<String> deleteTransaction(@RequestParam(value = "peso", defaultValue = "0") final int peso) {
		return new ResponseEntity<String>("delete " + peso, HttpStatus.NO_CONTENT);
	}
}
