package com.trantor.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trantor.entity.Opportunity;

public interface OpportunityRepo extends JpaRepository<Opportunity, Integer> {
	public Opportunity findById(int opp_id);
}