package com.org.entity;

import java.util.Set;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "HB_AM_OTM_EMPLOYEE_BD")
@Entity
public class Employee {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "EMP_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer empNo;
	private String empName;
	private Double empSalary;
	
	@ManyToOne(targetEntity = Department.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_NO", referencedColumnName = "DEPTNO") // If mappedBy attribute is placed in parent class of OneToMany annotation then this is optional
	@LazyToOne(LazyToOneOption.PROXY)
	private Department department; // For Many to One
	
	
	
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", empSalary=" + empSalary + "]";
	}



	public Employee() {
		System.out.println("Employee.Employee()  "+this.getClass());
	}
	
	
}
