package com.trantor.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

@RestController
public class OpportunityController {

	@Autowired
	private OpportunityService service;

	@PostMapping("/add")
	public ResponseEntity<?> addData(@Validated @RequestBody OpportunityRequest opportunityRequest) {
		Opportunity insertedData = service.insert(opportunityRequest);
		return new ResponseEntity<Opportunity>(insertedData, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> selectBank() {
		List<Opportunity> findAll = service.findAll();
		return new ResponseEntity<List<Opportunity>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/bank{opp_id}")
	public ResponseEntity<?> selectBank(@RequestParam int opp_id) {
		ResponseEntity<?> responseEntity;
		ArrayList<String> giveBnak = service.giveBnak(opp_id);
		Object[] array = giveBnak.toArray();
		String responseString = Arrays.toString(array);
		if (responseString == "Data not found") {
			responseEntity = new ResponseEntity<String>(responseString, HttpStatus.BAD_REQUEST);
		} else {
			responseEntity = new ResponseEntity<String>(responseString, HttpStatus.OK);
		}
		return responseEntity;
	}
}
