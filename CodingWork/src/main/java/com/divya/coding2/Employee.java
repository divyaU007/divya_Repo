package com.divya.coding2;

/**
 * @author divyauppalapati
 *
 */
public class Employee {

	private int eId;
	private String empName;
	private long empPhone;
	private String empAddress;

	public int getId() {
		return eId;
	}

	public void setId(int id) {
		this.eId = id;
	}

	public String getName() {
		return empName;
	}

	public void setName(String name) {
		this.empName = name;
	}

	public long getPhone() {
		return empPhone;
	}

	public void setPhone(long phone) {
		this.empPhone = phone;
	}

	public String getAddress() {
		return empAddress;
	}

	public void setAddress(String address) {
		this.empAddress = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + eId + ", name=" + empName + ", phone=" + empPhone + ", address=" + empAddress + "]";
	}

}