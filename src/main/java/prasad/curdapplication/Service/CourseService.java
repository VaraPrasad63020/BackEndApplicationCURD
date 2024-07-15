package prasad.curdapplication.Service;

import java.util.List;

import prasad.curdapplication.Entity.Course;

public interface CourseService {

	public Course upsert(Course course);
	
	public List<Course>getAllcourse();
	
	public Course getCourseById(Long cId);
	
	public String deleteCourse(Long cId);
	
}
