package com.org.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "HB_AM_OTM_DEPARTMENT_BD")
@Entity
public class Department implements IDepartment{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptNo;
	private String deptName;
	private String deptHead;
	private Set<Employee> employee; // For One to Many
	
	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", deptName=" + deptName + ", deptHead=" + deptHead + ", employee="
				+ employee + "]";
	}
	
}
