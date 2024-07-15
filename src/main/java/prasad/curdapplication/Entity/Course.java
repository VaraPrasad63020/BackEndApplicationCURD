package prasad.curdapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CourseDetails")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long cId;
	String courseName;
	Float courseFee;
	String duration;
		
}
