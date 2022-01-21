package com.trantor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trantor.dto.OpportunityRequest;
import com.trantor.entity.Opportunity;
import com.trantor.service.OpportunityService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class OpportunityController {

	private static final Logger LOGGER = LogManager.getLogger(OpportunityController.class);
	@Autowired
	private OpportunityService service;

	@PostMapping("/add")
	public ResponseEntity<?> addData(@Validated @RequestBody OpportunityRequest opportunityRequest) {
		LOGGER.info("Info we are in add controller");
		LOGGER.debug("Debug in add controller");
		LOGGER.error("Error in add controller");
		Opportunity insertedData = service.insert(opportunityRequest);
		return new ResponseEntity<Opportunity>(insertedData, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		LOGGER.info("Info we are in findall controller");
		List<Opportunity> findAll = service.findAll();
		return new ResponseEntity<List<Opportunity>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/findById{opp_id}")
	public ResponseEntity<?> findById(@RequestParam int opp_id) {
		LOGGER.info("Info we are in findById controller");
		Opportunity findByIdResponse = service.findById(opp_id);
		if (findByIdResponse == null) {
			return new ResponseEntity<String>("Data not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Opportunity>(findByIdResponse, HttpStatus.OK);
	}

	@GetMapping("/bank{opp_id}")
	public ResponseEntity<?> selectBank(@RequestParam int opp_id) throws IOException {
		LOGGER.info("Info we are in bank controller");
		ResponseEntity<?> responseEntity;
		String responseBank = service.giveBank(opp_id);
		responseEntity = new ResponseEntity<String>(responseBank, HttpStatus.OK);
		return responseEntity;
	}

}
