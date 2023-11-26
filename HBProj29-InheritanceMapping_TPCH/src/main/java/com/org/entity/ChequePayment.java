package com.org.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("cheque")
public class ChequePayment extends Payment {
	
	private Long chequeNo;
	private String chequeType;
	
	@Override
	public String toString() {
		return "ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + super.toString()+"]";
	}
	 
	
}
