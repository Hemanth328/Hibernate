package com.org.entity;

import java.util.Set;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "HB_AM_MTM_PATIENT_BD")
public class Patient {

	@Id
	@SequenceGenerator(name = "gen2", sequenceName = "PAT_SEQ", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	private Integer patId;
	private String patName;
	private String problem;
	
	@ManyToMany(targetEntity = Doctor.class, cascade = CascadeType.ALL, mappedBy = "patient")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Doctor> doctor;

	@Override
	public String toString() {
		return "Patient [patId=" + patId + ", patName=" + patName + ", problem=" + problem + "]";
	}
	
	
}
