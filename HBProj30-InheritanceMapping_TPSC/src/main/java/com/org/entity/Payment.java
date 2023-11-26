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
@Table(name = "HB_INHR_TPSC_PAYMENT")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

	@Id
	@GeneratedValue
	private Long txId;
	private Double amount;
	private LocalDateTime txDate;
	
}