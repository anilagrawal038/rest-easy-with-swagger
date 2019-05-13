package com.san.co;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.san.constants.Gender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Student Request Model")
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentCO {

	@NotEmpty(message = "name is requierd & must not be empty")
	@ApiModelProperty(value = "student's name", required = true, example = "xyz")
	@XmlAttribute
	private String name;

	@Range(min = 1, message = "roll number is required & must be greater than 0")
	@ApiModelProperty(value = "student's roll number (Minimum Value : 1)", required = true, example = "15")
	@XmlElement
	private int rollNumber;

	@Range(min = 1, max = 99, message = "age is required & must be between 1-99")
	@ApiModelProperty(value = "student's age (Range : 1-99)", required = true, example = "10")
	@XmlAttribute
	private int age;

	@ApiModelProperty(value = "student's gender", required = true)
	@XmlAttribute
	private Gender gender;

	@ApiModelProperty(value = "student's nationality", required = true)
	@JsonProperty(value = "isIndian")
	@XmlAttribute(name = "indian")
	private boolean indian;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean getIndian() {
		return indian;
	}

	public void setIndian(boolean indian) {
		this.indian = indian;
	}

}
