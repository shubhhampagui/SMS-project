package com.flipkart.rest;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.CourseEnrollmentService;
import com.flipkart.service.ServiceManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The type Course enrollment controller. All the operations related to enrollment of the Student and Professor
 * in the Course Management System
 */
@Path("/enrollment")
public class CourseEnrollmentController {
    private static final Logger logger = Logger.getLogger(CourseEnrollmentController.class);
    private final CourseEnrollmentService service;

    /**
     * Instantiates a new Course enrollment controller.
     */
    public CourseEnrollmentController() {
        ServiceManager.initialize();
        service = (CourseEnrollmentService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.COURSE_ENROLMENT);
    }

    /**
     * Register a new course for the student
     *
     * @param courseId  the course id
     * @param studentId the student id
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/register")
    public Response register(@QueryParam("courseId") int courseId, @QueryParam("studentId") int studentId) {
        int responseCode;
        String message;
        boolean result = service.registerCourse(studentId, courseId);
        if (result) {
            message = "Successfully registered the course.";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to register the course.";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Drop a course for the student
     *
     * @param courseId  the course id
     * @param studentId the student id
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/drop")
    public Response drop(@QueryParam("courseId") int courseId, @QueryParam("studentId") int studentId) {
        int responseCode;
        String message;
        boolean result = service.deleteCourse(studentId, courseId);
        if (result) {
            message = "Successfully dropped the course.";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to drop the course.";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).build();
    }

    /**
     * Assign grade to the student for mentioned course
     *
     * @param courseId  the course id
     * @param studentId the student id
     * @param grade     the grade
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/assigngrade")
    public Response assignGrade(@QueryParam("courseId") int courseId, @QueryParam("studentId") int studentId,
                                @QueryParam("grade") String grade) {
        int responseCode;
        String message;
        boolean result = service.assignGrade(studentId, courseId, grade);
        if (result) {
            message = "Successfully assigned the grade.";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to assigned the grade.";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).build();
    }

    /**
     * Assign a professor to given course
     *
     * @param courseId    the course id
     * @param professorId the professor id
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/assignprofessor")
    public Response assignProfessor(@QueryParam("courseId") int courseId, @QueryParam("professorId") int professorId) {
        int responseCode;
        String message;
        boolean result = service.assignProfessor(professorId, courseId);
        if (result) {
            message = "Successfully assigned the professor.";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to assign the professor.";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).build();
    }

    /**
     * Gets student information containing the registered course ids
     *
     * @param studentId the student id
     * @return the student containing user id and registered courses ids list
     */
    @GET
    @Path("/getstudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") int studentId) {
        logger.debug("Getting the information of the student");
        return service.getStudent(studentId);
    }

    /**
     * Gets professor information containing user id and the assigned courses list
     *
     * @param professorId the professor id
     * @return the professor containing the user id and assigned courses ids list
     */
    @GET
    @Path("/getprofessor/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") int professorId) {
        logger.debug("Getting information of the professor");
        return service.getProfessor(professorId);
    }
}