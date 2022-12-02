package com.food.pojo;

public class Customer {
	
	private int custId;
	private String custName;
	private long custPhone;
	private String custPass;
	private String custAddress;
	
	public Customer()
	{
		//just to create object without any parameter
	}
	public Customer(int custId, String custName, long custPhone, String custPass, String custAddress) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custPhone = custPhone;
		this.custPass = custPass;
		this.custAddress = custAddress;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public long getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(long custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustPass() {
		return custPass;
	}
	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custPhone=" + custPhone + ", custPass="
				+ custPass + ", custAddress=" + custAddress + "]";
	}
	
	
	
	

}
