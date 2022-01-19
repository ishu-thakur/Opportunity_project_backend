package com.trantor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantor.dao.OpportunityDAO;
import com.trantor.dto.OpportunityRequest;
import com.trantor.entity.Opportunity;
import com.trantor.entity.Opportunity_additional_details;

@Service
public class OpportunityService {
	@Autowired
	private OpportunityDAO dao;

	private ArrayList<String> bank = new ArrayList<String>();

	public ArrayList<String> giveBnak(int opp_id) {

		Opportunity giveBank = dao.giveBank(opp_id);
		if (giveBank == null) {
			bank.clear();
			bank.add("Data not found");
			return (bank);
		} else {
			bank.clear();
			Opportunity_additional_details opportunity_additional_details = giveBank.getAdditional_details().get(0);
			System.out.println("giveBank : " + giveBank);
			System.out.println("opportunity_additional_details : " + opportunity_additional_details);

			if (!giveBank.getMailing_state().equals("CO")) {

				bank.add("FEB");
				if (opportunity_additional_details.getGrade() == "A1"
						|| opportunity_additional_details.getGrade() == "B1"
						|| opportunity_additional_details.getGrade() == "A2"
						|| opportunity_additional_details.getGrade() == "B2"
						|| opportunity_additional_details.getGrade() == "C1"
								&& opportunity_additional_details.getFICO() > 575
								&& opportunity_additional_details.getProduct() == "Point of need") {
					bank.add("Costal");

				} else if (opportunity_additional_details.getGrade() == "A1"
						|| opportunity_additional_details.getGrade() == "B1"
						|| opportunity_additional_details.getGrade() == "A2"
						|| opportunity_additional_details.getGrade() == "B2"
						|| opportunity_additional_details.getGrade() == "C1"
								&& opportunity_additional_details.getFICO() > 550
								&& opportunity_additional_details.getProduct() == "Lending Point"
						|| opportunity_additional_details.getProduct() == "WC Free Based"
								&& giveBank.getMailing_state() != "NV"
						|| giveBank.getMailing_state() != "WY" || giveBank.getMailing_state() != "WV"
						|| giveBank.getMailing_state() != "VT") {
					bank.add("Costal");
				}
				return (bank);

			} else {
				bank.clear();
				bank.add("LP");
			}
			return (bank);

		}

	}

	public List<Opportunity> findAll() {
		return dao.findAll();
	}

	public Opportunity insert(OpportunityRequest opportunityRequest) {
		return dao.insert(opportunityRequest);
	}
}
