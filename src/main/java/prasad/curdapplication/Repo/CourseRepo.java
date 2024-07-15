package prasad.curdapplication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import prasad.curdapplication.Entity.Course;


public interface CourseRepo extends JpaRepository<Course, Long>{

}
