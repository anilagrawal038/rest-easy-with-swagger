package com.san.co;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Student Request Model")
public class StudentCO {

	@NotEmpty(message = "name is requierd & must not be empty")
	@ApiModelProperty(value = "student's name", required = true, example = "xyz")
	private String name;

	@Range(min = 1, message = "roll number is required & must be greater than 0")
	@ApiModelProperty(value = "student's roll number (Minimum Value : 1)", required = true, example = "15")
	private int rollNumber;

	@Range(min = 1, max = 99, message = "age is required & must be between 1-99")
	@ApiModelProperty(value = "student's age (Range : 1-99)", required = true, example = "10")
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
