package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HB_INHR_TPSC_CARDPAYMENT")
@PrimaryKeyJoinColumn(name = "PAYMENT_ID", referencedColumnName = "TXID")
public class CardPayment extends Payment {
	
	private Long cardNo;
	private String cardType;
	private String gateway;
	@Override
	public String toString() {
		return "CardPayment [cardNo=" + cardNo + ", cardType=" + cardType + ", gateway=" + gateway + super.toString()+ "]";
	}
	
	

}
