package com.org.entity;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "HB_AM_OTM_DEPARTMENT_BD")
@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptNo;
	private String deptName;
	private String deptHead;
	
	@OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, mappedBy = "department") // mappedBy attribute name should be same as parent variable name in child class
//	@JoinColumn(name = "DEPARTMENT_NO", referencedColumnName = "DEPTNO") // If mappedBy attribute is placed then this line is optional
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Employee> employee; // For One to Many
	
	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", deptName=" + deptName + ", deptHead=" + deptHead + "]";
	}

	public Department() {
		System.out.println("Department.Department()  "+this.getClass());
	}
	
	
}
