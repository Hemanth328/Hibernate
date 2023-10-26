package com.org.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PROGRAMPROJECT_INFO")
public class PrgmProjectInfo {

	@EmbeddedId
	private PrgmProjId id;
	
	private String prgmrName;
	
	private String projName;
	
	private Double salary;
	
	private Double budget;
	
}
