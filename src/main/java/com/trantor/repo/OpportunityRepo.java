package com.trantor.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trantor.entity.Opportunity;

@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Integer> {
	public Opportunity findById(int opp_id);
}