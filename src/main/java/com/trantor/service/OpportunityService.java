package com.trantor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantor.dao.OpportunityDAO;
import com.trantor.dto.OpportunityRequest;
import com.trantor.entity.Audit;
import com.trantor.entity.Opportunity;
import com.trantor.entity.Opportunity_additional_details;
import com.trantor.repo.AuditRepo;

@Service
public class OpportunityService {

	private static final Logger LOGGER = LogManager.getLogger(OpportunityService.class);

	@Autowired
	private AuditRepo auditRepo;

	@Autowired
	private OpportunityDAO dao;

	private String bank;
	private String grade[] = { "A1", "A2", "B1", "B2", "C1", "C2" };
	private String state[] = { "NV", "WY", "WV", "VT" };
	private String product[] = { "Lending Point", "WC Free Based" };

	public String giveBank(int opp_id) throws IOException {

		Audit audit = new Audit();

		String url = "http://localhost:3000/findById?opp_id=";
		URL reterievedJSONData = new URL(url + opp_id);
		HttpURLConnection con = (HttpURLConnection) reterievedJSONData.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();

		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("Response : " + response.toString());
		Opportunity opportunityResponse = objectMapper.readValue(response.toString(), Opportunity.class);
		System.out.println("opportunityResponse : " + opportunityResponse);

		Opportunity_additional_details opportunity_additional_details = opportunityResponse.getAdditional_details()
				.get(0);
		System.out.println("opportunity_additional_details.getFICO() : " + opportunity_additional_details.getFICO());
		if (opportunity_additional_details.getFICO() > 575
				&& Arrays.asList(grade).contains(opportunity_additional_details.getGrade())
				&& opportunity_additional_details.getProduct() == "Point of need") {
			System.out.println("Costal 1");
			bank = "Costal";

		} else if (opportunity_additional_details.getFICO() > 550
				&& Arrays.asList(grade).contains(opportunity_additional_details.getGrade())
				&& !Arrays.asList(product).contains(opportunity_additional_details.getProduct())
				&& !Arrays.asList(state).contains(opportunityResponse.getMailing_state())) {
			bank = "Costal";
			System.out.println("costal 2");

		} else if (!opportunityResponse.getMailing_state().equals("CO")) {
			bank = "FEB";
			System.out.println("FEB");

		} else {
			bank = "LP";
			System.out.println("LP");

		}
		LocalDateTime time = java.time.LocalDateTime.now();
		audit.setOpp_id(String.valueOf(opp_id));
		audit.setCreated_by(opportunityResponse.getFirst_name());
		audit.setCreated_on(time);
		audit.setResponse(bank);
		auditRepo.save(audit);
		LOGGER.info("Info we are in bank service");
		return bank;
	}

	public List<Opportunity> findAll() {
		LOGGER.info("Info we are in finAll service");
		return dao.findAll();
	}

	public Opportunity insert(OpportunityRequest opportunityRequest) {
		LOGGER.info("Info we are in insert service");
		return dao.insert(opportunityRequest);
	}

	public Opportunity findById(int opp_id) {
		LOGGER.info("Info we are in findById service");
		return dao.findById(opp_id);
	}

}
