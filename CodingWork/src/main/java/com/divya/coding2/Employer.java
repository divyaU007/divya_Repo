package com.divya.coding2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author divyauppalapati
 *
 */
public class Employer implements Cloneable {

	private String eName;
	private List<Employee> employee = new ArrayList<Employee>();

	public String getEmployerName() {
		return eName;
	}

	public void setEmployerName(String employerName) {
		this.eName = employerName;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	/**
	 * Load data
	 */
	public void loadData() {

		for (int i = 1; i <= 10; i++) {
			Employee emp = new Employee();
			emp.setId(i);
			emp.setName(" Employee " + i);
			emp.setPhone(Long.parseLong("12345678") + i);
			emp.setAddress("VA" + i);
			getEmployee().add(emp);

		}
	}

	@Override
	public String toString() {
		return "Employer [employerName=" + eName + ", employee=" + employee + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null ) {
			return false;
		}
		
		Employer employer = (Employer) obj;
		
		return (employer.getEmployee() == this.getEmployee());
	}

}