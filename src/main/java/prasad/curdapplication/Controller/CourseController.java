package prasad.curdapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prasad.curdapplication.Entity.Course;
import prasad.curdapplication.Service.CourseServiceImpl;


@RestController
@CrossOrigin
public class CourseController {
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@PostMapping("/save")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		Course save = courseService.upsert(course);
		return new ResponseEntity<>(save, HttpStatus.CREATED);		
	}
	
	@GetMapping("/course/{cid}")
		public ResponseEntity<Course> getAllCourse(@PathVariable Long cid){
		Course courseById = courseService.getCourseById(cid);
		return new ResponseEntity<>(courseById,HttpStatus.OK);
	}
	
	@GetMapping("/course")
	public ResponseEntity<List<Course>> getAllCourseById(){
		 List<Course> allcourse = courseService.getAllcourse();
		return new ResponseEntity<>(allcourse,HttpStatus.OK);
	}
	
	@DeleteMapping("/course/{cid}")
	public ResponseEntity<String>deleteCourse(@PathVariable Long cid){
		String deleteCourse = courseService.deleteCourse(cid);
		return new ResponseEntity<String>("Record is deleted",HttpStatus.OK);
	}
	@PutMapping("/course")
	public ResponseEntity<Course>updateCourse(@RequestBody Course course){
		Course status = courseService.upsert(course);
		return new ResponseEntity<Course>(status, HttpStatus.OK);
	}
		 
	

}
