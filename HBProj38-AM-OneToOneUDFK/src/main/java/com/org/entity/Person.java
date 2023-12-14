package com.org.entity;

import java.io.Serializable;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HB_AM_OTO_PERSON_FK_UD")
public class Person implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;
	private String pName;
	private String pAddrs;
	
	
	// This is for Bi-Directional
	@OneToOne(targetEntity = Passport.class, cascade = CascadeType.ALL, mappedBy = "personDetails")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Passport passport;
	

	@Override
	public String toString() {
		return "Person [pId=" + pId + ", pName=" + pName + ", pAddrs=" + pAddrs + "]";
	}
	
	
	
}
