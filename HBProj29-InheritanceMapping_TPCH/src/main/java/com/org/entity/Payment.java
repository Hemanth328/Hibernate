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
@Table(name = "HB_INHR_TPCH_PAYMENT")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAYMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("payment") // optional cause the class is abstract
public abstract class Payment {

	@Id
	@GeneratedValue
	private Long txId;
	private Double amount;
	private LocalDateTime txDate;
	
}