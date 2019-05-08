package com.san.to;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Students", description = "Students List Model")
public class StudentsTO {

	@ApiModelProperty(value = "students list")
	private List<StudentTO> students = new ArrayList<StudentTO>();

	public List<StudentTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentTO> students) {
		this.students = students;
	}

}
