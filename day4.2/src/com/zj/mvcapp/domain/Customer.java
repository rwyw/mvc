package com.zj.mvcapp.domain;

public class Customer {

	private String id;
	private String name;
	private Integer age;
	private String address;
	private String phone;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer(String name, Integer age, String address,
			String phone) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}

	
	public Customer(String id, String name, Integer age, String address,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Customer() {
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age
				+ ", address=" + address + ", phone=" + phone + "]";
	}

}
