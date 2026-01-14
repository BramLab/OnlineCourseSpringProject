package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.CourseRequest;
import org.intecbrussel.onlinecoursespringproject.dto.CourseResponse;
import org.intecbrussel.onlinecoursespringproject.repository.CourseRepository;
import org.intecbrussel.onlinecoursespringproject.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }
    //CourseController
    //Methode Endpoint            Auth
    //GET     /api/courses        public
    //GET     /api/courses/{id}   public
    //POST    /api/courses        INSTRUCTOR/ADMIN
    //PUT     /api/courses/{id}   INSTRUCTOR (own) / ADMIN
    //DELETE  /api/courses/{id}   ADMIN
    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable long id, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
    }

}
