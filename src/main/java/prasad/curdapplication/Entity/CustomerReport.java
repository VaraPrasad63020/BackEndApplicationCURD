package prasad.curdapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customer_report")
public class CustomerReport {
	@Id
	Long id;
	String empName;
	String empExp;
	String phoneNumber;
	float salary;	
		
}
