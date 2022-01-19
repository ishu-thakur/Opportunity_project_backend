package com.trantor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trantor.dto.OpportunityRequest;
import com.trantor.entity.Opportunity;
import com.trantor.repo.OpportunityRepo;

@Repository
public class OpportunityDAO {

	@Autowired
	private OpportunityRepo opportunityRepo;

	@Transactional
	public Opportunity giveBank(int opp_id) {
		Opportunity findById = opportunityRepo.findById(opp_id);
		Opportunity response;
		if (findById == null) {
			response = null;
		} else {
			response = opportunityRepo.findById(opp_id);
		}
		return response;
	}

	@Transactional
	public List<Opportunity> findAll() {
		return opportunityRepo.findAll();
	}

	@Transactional
	public Opportunity insert(OpportunityRequest opportunityRequest) {
		return opportunityRepo.save(opportunityRequest.getOpportunity());
	}

}
