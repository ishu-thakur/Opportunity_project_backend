package com.trantor.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "opp_tbl")
public class Opportunity implements Serializable{
	
	@Id
	private int opp_id;
	private String opp_uuid;
	private String first_name;
	private String last_name;
	private String mailing_state;
	private String source;
	private String partner_name;

	@OneToMany(targetEntity = Opportunity_additional_details.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fK", referencedColumnName = "opp_uuid")
	private List<Opportunity_additional_details> additional_details;
}
