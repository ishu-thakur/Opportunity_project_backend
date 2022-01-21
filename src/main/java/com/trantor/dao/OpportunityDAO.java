package com.trantor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trantor.dto.OpportunityRequest;
import com.trantor.entity.Opportunity;
import com.trantor.repo.OpportunityRepo;

@Repository
public class OpportunityDAO {

	private static final Logger LOGGER = LogManager.getLogger(OpportunityDAO.class);
	@Autowired
	private OpportunityRepo opportunityRepo;

	@Transactional
	public List<Opportunity> findAll() {
		LOGGER.info("Info we are in findAll DAO");
		return opportunityRepo.findAll();
	}

	@Transactional
	public Opportunity findById(int opp_id) {
		Opportunity response = opportunityRepo.findById(opp_id);
		LOGGER.info("Info we are in findById DAO");
		if (response == null) {
			return null;
		} else {
			return opportunityRepo.findById(opp_id);
		}
	}

	@Transactional
	public Opportunity insert(OpportunityRequest opportunityRequest) {
		LOGGER.info("Info we are in insert DAO");
		return opportunityRepo.save(opportunityRequest.getOpportunity());
	}

}
