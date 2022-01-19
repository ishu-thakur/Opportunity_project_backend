package com.trantor.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "opp_addn_tbl")
public class Opportunity_additional_details implements Serializable {

	@Id
	private int opp_addn_id;
	private int FICO;
	private String product;
	private String loan_amount;
	private String grade;
	private String loan_term;

}
