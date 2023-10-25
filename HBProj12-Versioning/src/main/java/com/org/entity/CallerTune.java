package com.org.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "CALLERTUNE_INFO")
@Data
public class CallerTune {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tuneId;
	
	private String tuneName;
	
	private String movieName;
	
	@Version
	@Column(name = "UPDATE_COUNT")
	private Integer updationCount;

}
