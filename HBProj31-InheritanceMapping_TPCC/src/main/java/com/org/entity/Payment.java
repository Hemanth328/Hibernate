package com.org.entity;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HB_INHR_TPCC_PAYMENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment {

	/*
	 * As this is an Abstract class this will be an empty DB Table 
	 */
	@Id
	@GeneratedValue
	private Long txId;
	private Double amount;
	private LocalDateTime txDate;
	
}