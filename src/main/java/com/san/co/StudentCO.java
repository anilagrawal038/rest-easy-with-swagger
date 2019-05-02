package com.san.co;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class StudentCO {

	@NotEmpty(message = "name is requierd & must not be empty")
	private String name;

	@Range(min = 1, message = "roll number is required & must be greater than 0")
	private int rollNumber;

	@Range(min = 1, message = "age is required & must be greater than 0")
	private byte age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

}
