package com.org.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "CALLERTUNE_INFO")
@Data
public class CallerTune {
	
	@Id
	@GeneratedValue
	private Integer tuneId;
	
	private String tuneName;
	
	private String movieName;
	
	@Version
	@Column(name = "UPDATE_COUNT")
	private Integer updationCount;
	
	@CreationTimestamp
	private Timestamp launchTime;
	
	@UpdateTimestamp
	private Timestamp updationTime;

}
