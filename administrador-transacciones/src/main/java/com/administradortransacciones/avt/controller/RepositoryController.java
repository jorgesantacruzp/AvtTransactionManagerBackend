package com.administradortransacciones.avt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administradortransacciones.avt.common.util.RepositoryUtil;
import com.administradortransacciones.avt.service.RepositoryService;

@RestController
public class RepositoryController {

	@Autowired
	private RepositoryService repositoryService;

	@GetMapping("/v1/repositories")
	public ResponseEntity<String> getActiveRepository() {
		return new ResponseEntity<String>(RepositoryUtil.getChosenRepository(), HttpStatus.OK);
	}

	@PostMapping("/v1/repositories/{repository}")
	public ResponseEntity<String> saveRepository(@PathVariable(value = "repository") final String repository) {
		repositoryService.saveRepositoryInMemory(repository);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
