package prasad.curdapplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prasad.curdapplication.Entity.Course;
import prasad.curdapplication.Repo.CourseRepo;
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public Course upsert(Course course) {
		Course save = courseRepo.save(course);
		return save;
	}
	@Override
	public List<Course> getAllcourse() {
		List<Course> all = courseRepo.findAll();
		return all;
	}
	@Override
	public Course getCourseById(Long cId) {
		Optional<Course> byId = courseRepo.findById(cId);
		if(courseRepo.findById(cId).isPresent())
			return byId.get();
		return null;
	}
	@Override
	public String deleteCourse(Long cId) {
		if(courseRepo.findById(cId).isPresent())
			courseRepo.deleteById(cId);
		return "The id is not found";
	}
}
