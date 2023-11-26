package com.org.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HB_INHR_TPCC_CHEQUEPAYMENT")
public class ChequePayment extends Payment {
	
	private Long chequeNo;
	private String chequeType;
	
	@Override
	public String toString() {
		return "ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + super.toString()+"]";
	}
	 
	
}
