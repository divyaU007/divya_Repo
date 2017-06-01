package com.divya.coding2;

import org.apache.log4j.Logger;

/**
 * @author divyauppalapati
 *
 */
public class EmployerDetails {

	public static void main(String args[]) {

		final Logger logger = Logger.getLogger(EmployerDetails.class);

		Employer emp1 = new Employer();
		emp1.setEmployerName("XYZ Shipping Corporation");
		//load data
		emp1.loadData();
		
		
		Employer emp2= null;
		try {
			emp2 = (Employer) emp1.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		emp2.setEmployerName("HCL America");
		
		logger.debug(" Hashcode  of Emp1" + emp1.getEmployee().hashCode());
		logger.debug(" Hashcode of Emp2 " +emp2.getEmployee().hashCode());
		
		if(emp1.equals(emp2)) {
			logger.debug("employees are same");
		} else {
			logger.debug("employees are not same");
		}

	}

}