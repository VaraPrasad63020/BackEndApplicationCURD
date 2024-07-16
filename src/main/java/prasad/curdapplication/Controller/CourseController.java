package prasad.curdapplication.Controller;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLanucher;

	@PostMapping("/save")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course save = courseService.upsert(course);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/course/{cid}")
	public ResponseEntity<Course> getAllCourse(@PathVariable Long cid) {
		Course courseById = courseService.getCourseById(cid);
		return new ResponseEntity<>(courseById, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<List<Course>> getAllCourseById() {
		List<Course> allcourse = courseService.getAllcourse();
		return new ResponseEntity<>(allcourse, HttpStatus.OK);
	}

	@DeleteMapping("/course/{cid}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long cid) {
		String deleteCourse = courseService.deleteCourse(cid);
		return new ResponseEntity<String>("Record is deleted" + deleteCourse, HttpStatus.OK);
	}

	@PutMapping("/course")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course status = courseService.upsert(course);
		return new ResponseEntity<Course>(status, HttpStatus.OK);
	}

	@PostMapping("/batch")
	public void importCSVtoDBJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		jobLanucher.run(job, jobParameters);
	}

}
