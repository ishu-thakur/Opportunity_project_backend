package com.trantor.dto;

import com.trantor.entity.Opportunity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpportunityRequest {

	private Opportunity opportunity;
}
