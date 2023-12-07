package com.org.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "HB_AM_OTM_PHONENUMBER_UD")
public class PhoneNumber implements Serializable {
	
	@Id
	@SequenceGenerator(name = "gen", sequenceName = "PHONE_SEQ", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.SEQUENCE)
	private Integer regNo;
	private Long mobileNo;
	private String numberType;
	private String provider;
	
	
	// Taking property for FK Column is purely optional
	
	public PhoneNumber() {
		System.out.println("PhoneNumber.PhoneNumber() 0 Param constructor "+this.getClass());
	}
	
}
