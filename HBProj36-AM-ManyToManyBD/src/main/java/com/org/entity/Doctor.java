package com.org.entity;

import java.util.Set;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HB_AM_MTM_DOCTOR_BD")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;
	private String docName;
	private String hospital;
	@ManyToMany(targetEntity = Patient.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "MTM_DOCTORS_PATIENTS", 
	           joinColumns = @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCID"),
	           inverseJoinColumns = @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATID"))
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Patient> patient; // To hold set of doctors
	
	@Override
	public String toString() {
		return "Doctor [docId=" + docId + ", docName=" + docName + ", hospital=" + hospital + "]";
	}
	
	
	
}
