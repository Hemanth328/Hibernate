package com.org.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrgmProjId implements Serializable {
	
	@Column(name = "PRGMR_ID")
	private Integer prgmrId;
	
	@Column(name = "PROJ_ID")
	private Integer projId;

}
