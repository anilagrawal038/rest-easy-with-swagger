package com.san.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.san.constants.Gender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Student", description = "Student Details Model")
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentTO {

	private static long _studentIdCounter = 0;

	@ApiModelProperty(value = "student's unique id")
	@XmlAttribute
	private long id = ++_studentIdCounter;

	@ApiModelProperty(value = "student's name")
	@XmlAttribute
	private String name;

	@ApiModelProperty(value = "student's roll number")
	@XmlElement
	private int rollNumber;

	@ApiModelProperty(value = "student's age")
	@XmlAttribute
	private int age;

	@ApiModelProperty(value = "student's gender")
	@XmlAttribute
	private Gender gender;

	@ApiModelProperty(value = "student's nationality")
	@JsonProperty(value = "indian")
	@XmlAttribute(name = "isIndian")
	private boolean indian;

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
