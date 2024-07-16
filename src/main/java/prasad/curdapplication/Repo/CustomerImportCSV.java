package prasad.curdapplication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import prasad.curdapplication.Entity.CustomerReport;

public interface CustomerImportCSV extends JpaRepository<CustomerReport, Long>{

}
