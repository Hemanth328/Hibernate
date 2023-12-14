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
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "HB_AM_OTO_STUDENT_PK_BD")
public class Student implements Serializable {

	@Id
	@SequenceGenerator(name = "gen", sequenceName = "STD_SEQ", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "gen", strategy = GenerationType.SEQUENCE)
	private Integer sno;
	private String sname;
	private String sadd;
	
	@OneToOne(targetEntity = LibraryMemberShip.class, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "LID", referencedColumnName = "sno")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private LibraryMemberShip libraryDetails;
	
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sadd=" + sadd + "]";
	}	
}
