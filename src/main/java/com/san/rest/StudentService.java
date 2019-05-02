package com.san.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import com.san.co.StudentCO;
import com.san.to.ErrorTO;
import com.san.to.ResponseTO;
import com.san.to.StudentTO;
import com.san.to.StudentsTO;
import com.san.util.CommonUtil;

@Path("/student")
public class StudentService {

	Map<Long, StudentTO> studentsMap = new HashMap<Long, StudentTO>();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listStudents() {
		StudentsTO studentsTO = new StudentsTO();
		List<StudentTO> studentsList = studentsTO.getStudents();
		for (Long studentId : studentsMap.keySet()) {
			studentsList.add(studentsMap.get(studentId));
		}
		return Response.ok(studentsTO).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ValidateRequest
	public Response addStudent(@Valid StudentCO studentCO) {
		StudentTO student = new StudentTO();
		CommonUtil.mapProperties(studentCO, student);
		studentsMap.put(student.getId(), student);
		return Response.ok(student).build();
	}

	@GET
	@Path("/search")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response searchStudents(StudentCO studentCO) {
		StudentsTO studentsTO = new StudentsTO();
		List<StudentTO> studentsList = studentsTO.getStudents();
		for (Long studentId : studentsMap.keySet()) {
			StudentTO student = studentsMap.get(studentId);
			if (studentCO.getAge() > 0 && student.getAge() != studentCO.getAge()) {
				continue;
			}
			if (studentCO.getRollNumber() > 0 && student.getRollNumber() != studentCO.getRollNumber()) {
				continue;
			}
			if (studentCO.getName() != null && studentCO.getName().length() > 0 && !student.getName().equals(studentCO.getName())) {
				continue;
			}
			studentsList.add(student);
		}
		return Response.ok(studentsTO).build();
	}

	@GET
	@Path("{id:\\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getStudent(@PathParam("id") long id) {
		StudentTO student = studentsMap.get(id);
		if (student != null) {
			return Response.ok(student).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorTO("No student exists with id : " + id)).build();
		}
	}

	@PUT
	@Path("{id:\\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ValidateRequest
	public Response updateStudent(@PathParam("id") long id, @Valid StudentCO studentCO) {
		StudentTO student = studentsMap.get(id);
		if (student != null) {
			CommonUtil.mapProperties(studentCO, student);
			return Response.ok(student).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorTO("No student exists with id : " + id)).build();
		}
	}

	@DELETE
	@Path("{id:\\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteStudent(@PathParam("id") long id, StudentCO studentCO) {
		StudentTO student = studentsMap.get(id);
		if (student != null) {
			studentsMap.remove(id);
			return Response.ok(new ResponseTO("Student record deleted for id : " + id)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorTO("No student exists with id : " + id)).build();
		}
	}

}
