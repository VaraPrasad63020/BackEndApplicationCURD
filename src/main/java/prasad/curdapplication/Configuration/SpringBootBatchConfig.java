package prasad.curdapplication.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import prasad.curdapplication.Entity.CustomerReport;
import prasad.curdapplication.Repo.CustomerImportCSV;

@Configuration
public class SpringBootBatchConfig {

	@Autowired
	CustomerImportCSV customerImportRepo;

    @Bean
    FlatFileItemReader<CustomerReport> reader(){
		
		FlatFileItemReader<CustomerReport> itemreader= new FlatFileItemReader<>();
		itemreader.setResource(new FileSystemResource("src/main/resources/customerinfo.csv"));//set the path
		itemreader.setName("csvimporter");//give the name of importer
		itemreader.setLinesToSkip(1);// Lines to skip
		itemreader.setLineMapper(lineMapper());// Mapper of CSV file 
		
		return itemreader;
		
	
	}
	
	
	private LineMapper<CustomerReport> lineMapper() {
		DefaultLineMapper<CustomerReport>linemapper=new DefaultLineMapper<CustomerReport>();//Mapper style
		DelimitedLineTokenizer token= new DelimitedLineTokenizer();
		token.setDelimiter(",");//separate with comma
		token.setStrict(false);
		token.setNames("id","empName","empExp","phoneNumber","salary");// the feilds
		BeanWrapperFieldSetMapper<CustomerReport> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(CustomerReport.class);
		linemapper.setFieldSetMapper(fieldSetMapper);
		linemapper.setLineTokenizer(token);
		return linemapper;
		
	} 
	@Bean
	public CustomerProcessor processer() {
		return new CustomerProcessor();
	}
	public RepositoryItemWriter<CustomerReport> writer(){
		RepositoryItemWriter<CustomerReport> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerImportRepo);
		writer.setMethodName("save");
		return writer;
		
	}
	@Bean
	public Step step1(JobRepository jpaRepository,PlatformTransactionManager platformTransaction) {
		return new StepBuilder("CSV-Setup",jpaRepository)
				.<CustomerReport,CustomerReport>chunk(10,platformTransaction)
				.reader(reader())
				.processor(processer())
				.writer(writer())
				.build();
			
	}
	@Bean
	public Job runJob(JobRepository jpaRepository,PlatformTransactionManager platformTransaction) {
		return new JobBuilder("improt Customers",jpaRepository)
				.flow(step1(jpaRepository,platformTransaction)).end().build();
		
	}
}

