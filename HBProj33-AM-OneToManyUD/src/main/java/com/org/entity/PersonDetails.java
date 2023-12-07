package com.org.entity;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "HB_AM_OTM_PERSONDETAILS_UD") 
public class PersonDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String pname;
	private String paddrs;
	
	// Special Property to Hold bunch of child class objs (One to Many Association)
	@OneToMany(targetEntity = PhoneNumber.class, cascade = CascadeType.ALL,
			   fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "PID")
	@LazyCollection(LazyCollectionOption.EXTRA) // this will dominate the JPA FetchType
	private Set<PhoneNumber> phoneNumbers;

	public PersonDetails() {
		System.out.println("PersonDetails.PersonDetails() 0 Param Constructor "+this.getClass());
	}

	@Override
	public String toString() {
		return "PersonDetails [pid=" + pid + ", pname=" + pname + ", paddrs=" + paddrs + "]";
	}

		
}

// AM => Association Mapping
// OTM => One to Many Mapping
