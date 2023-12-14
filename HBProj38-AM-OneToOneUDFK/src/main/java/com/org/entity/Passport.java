package com.org.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HB_AM_OTO_PASSPORT_FK_UD")
public class Passport implements Serializable {

	@Id
	@SequenceGenerator(name = "gen", sequenceName = "PASSPORT_SEQ", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.SEQUENCE)
	private Integer passportNo;
	private String country;
	private LocalDate expiryDate;
	
	@OneToOne(targetEntity = Person.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "PID")
	private Person personDetails; // for One To One Association

	@Override
	public String toString() {
		return "Passport [passportNo=" + passportNo + ", country=" + country + ", expiryDate=" + expiryDate + "]";
	}
	
}
