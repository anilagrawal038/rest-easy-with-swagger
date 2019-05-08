package com.san.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Student", description = "Student Details Model")
public class StudentTO {

	private static long _studentIdCounter = 0;

	@ApiModelProperty(value = "student's unique id")
	private long id = ++_studentIdCounter;

	@ApiModelProperty(value = "student's name")
	private String name;

	@ApiModelProperty(value = "student's roll number")
	private int rollNumber;

	@ApiModelProperty(value = "student's age")
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
