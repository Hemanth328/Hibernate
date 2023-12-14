package com.org.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HB_AM_OTO_LIBMEMSP_PK_BD")
public class LibraryMemberShip  implements Serializable{

	@GenericGenerator(name = "gen", strategy = "foreign",
	                  parameters = @Parameter(name="property", value = "studentDetails"))
	@GeneratedValue(generator = "gen")
	@Id
	private Integer lid;
	private String type;
	private LocalDate doj;
	
	@OneToOne(targetEntity = Student.class, cascade = CascadeType.ALL, mappedBy = "libraryDetails")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Student studentDetails;
	
	@Override
	public String toString() {
		return "LibraryMemberShip [lid=" + lid + ", type=" + type + ", doj=" + doj + "]";
	}	
	
}
