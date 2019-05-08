package com.san.co;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Search Student Request Model")
public class SearchStudentCO {

	@ApiModelProperty(value = "student's name", example = "xyz")
	private String name;

	@ApiModelProperty(value = "student's roll number", example = "20")
	private int rollNumber;

	@ApiModelProperty(value = "student's age", example = "12")
	private int age;

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
