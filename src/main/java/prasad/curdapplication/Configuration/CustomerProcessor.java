package prasad.curdapplication.Configuration;

import org.springframework.batch.item.ItemProcessor;

import prasad.curdapplication.Entity.CustomerReport;

public class CustomerProcessor implements ItemProcessor<CustomerReport, CustomerReport> {

	public CustomerReport process(CustomerReport customer)throws Exception{
		return customer;
	}
}
