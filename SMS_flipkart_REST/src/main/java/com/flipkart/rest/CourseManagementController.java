package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.service.CourseManagementService;
import com.flipkart.service.ServiceManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The Course Management controller. All the operations for creating, getting, listing, deleting and updating
 * courses into course catalog.
 */
@Path("/course")
public class CourseManagementController {
    private static final Logger logger = Logger.getLogger(CourseManagementController.class);
    private final CourseManagementService service;

    /**
     * Instantiates a new Course Management controller.
     */
    public CourseManagementController() {
        ServiceManager.initialize();
        service = (CourseManagementService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.COURSE_MANAGEMENT);
    }

    /**
     * Add a new course into course catalog
     *
     * @param course the course to be added
     * @return the http response containing message text and response code
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createCourse(Course course) {
        int responseCode;
        String message;
        boolean result = service.addCourse(course);
        if (result) {
            message = "Successfully added the course";
            logger.debug(message);
            responseCode = 201;
        } else {
            message = "Error to add the course";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Gets course from course id
     *
     * @param courseId the course id
     * @return the course
     */
    @GET
    @Path("/get/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("courseId") int courseId) {
        logger.debug("Getting the required course");
        return service.getCourse(courseId);
    }

    /**
     * Get list of all the available course in course catalog
     *
     * @return the courses list
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        logger.debug("Getting the list of all the courses");
        return service.getCourses();
    }

    /**
     * Delete a course from course id
     *
     * @param courseId the course id of the course to be deleted
     * @return the http response containing message text and response code
     */
    @DELETE
    @Path("/delete/{courseId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@PathParam("courseId") int courseId) {
        int responseCode;
        String message;
        boolean result = service.deleteCourse(courseId);
        if (result) {
            message = "Successfully deleted the course";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to delete the course";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Update information of the course
     *
     * @param course the course containing updated information
     * @return the course the updated course information
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(Course course) {
        boolean result = service.updateCourse(course);
        if (result) {
            logger.debug("Successfully updated the course");
        } else {
            logger.error("Error occurred to update the course");
        }

        return service.getCourse(course.getId());
    }
}