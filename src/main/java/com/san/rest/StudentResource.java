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

import com.san.co.SearchStudentCO;
import com.san.co.StudentCO;
import com.san.to.ErrorTO;
import com.san.to.ResponseTO;
import com.san.to.StudentTO;
import com.san.to.StudentsTO;
import com.san.util.CommonUtil;

import io.swagger.annotations.*;

@Path("/student")
@Produces({ MediaType.APPLICATION_JSON })
@Api(value = "Manage Students")
public class StudentResource {

	Map<Long, StudentTO> studentsMap = new HashMap<Long, StudentTO>();

	@GET
	@ApiOperation(value = "Fetch students list", response = StudentsTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response listStudents() {
		StudentsTO studentsTO = new StudentsTO();
		List<StudentTO> studentsList = studentsTO.getStudents();
		for (Long studentId : studentsMap.keySet()) {
			studentsList.add(studentsMap.get(studentId));
		}
		return Response.ok(studentsTO).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@ValidateRequest
	@ApiOperation(value = "Add new student", response = StudentTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response addStudent(@ApiParam(required = true, value = "student details") @Valid StudentCO studentCO) {
		StudentTO student = new StudentTO();
		CommonUtil.mapProperties(studentCO, student);
		studentsMap.put(student.getId(), student);
		return Response.ok(student).build();
	}

	@GET
	@Path("/search")
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Find students", response = StudentsTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response searchStudents(@ApiParam(required = true, value = "student details to find students") SearchStudentCO studentCO) {
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
	@ApiOperation(value = "Fetch student details", response = StudentTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response getStudent(@ApiParam(required = true, value = "student's unique id") @PathParam("id") long id) {
		StudentTO student = studentsMap.get(id);
		if (student != null) {
			return Response.ok(student).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorTO("No student exists with id : " + id)).build();
		}
	}

	@PUT
	@Path("{id:\\d+}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@ValidateRequest
	@ApiOperation(value = "Update student details", response = StudentTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response updateStudent(@ApiParam(required = true, value = "student's unique id") @PathParam("id") long id, @ApiParam(required = true, value = "student details to be updated") @Valid StudentCO studentCO) {
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
	@ApiOperation(value = "Delete student entry", response = ResponseTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response deleteStudent(@ApiParam(required = true, value = "student's unique id") @PathParam("id") long id) {
		StudentTO student = studentsMap.get(id);
		if (student != null) {
			studentsMap.remove(id);
			return Response.ok(new ResponseTO("Student record deleted for id : " + id)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorTO("No student exists with id : " + id)).build();
		}
	}

}
