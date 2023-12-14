package com.org.entity;

import java.util.Set;

public interface IDepartment {
		
	public Integer getDeptNo();
	
	public void setDeptNo(Integer no);
	
	public String getDeptName();
	
	public void setDeptName(String name);
	
	public String getDeptHead();
	
	public void setDeptHead(String name);
	
	public Set<Employee> getEmployee();
	
	public void setEmployee(Set<Employee> list);
	
	
	
	
}
