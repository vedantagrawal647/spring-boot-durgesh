package com.batch.batch_application.config;

import com.batch.batch_application.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


/*
                        Key Concepts and Flow:

    The BatchConfig class sets up a Spring Batch job (importCustomerJob) that consists of a single step (step1).
    step1 involves reading from a CSV file (reader), processing each line (processor), and writing to a database (writer).
    The CustomerItemProcessor class is used to apply any business logic or transformation to each Customer object before persisting it.


 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
   private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //Reader
    public FlatFileItemReader<Customer> reader(){

        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>(); //It configures a FlatFileItemReader to read data from "customers.csv".
        reader.setResource(new ClassPathResource("customers.csv"));
        reader.setLineMapper(getLineMapper()); //read line
        reader.setLinesToSkip(1);  //skip one line
        System.out.println("reader created");
        return reader;
    }



    //It defines a LineMapper (DefaultLineMapper) for mapping lines from the CSV to Customer objects.
    private LineMapper<Customer> getLineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(); //line token is basically used to split through the data in row in .csv it split through ','
        lineTokenizer.setNames(new String[]{"customerid","firstname","lastname"});
        lineTokenizer.setIncludedFields(new int[]{1,2,3}); //which column no. mapped

        BeanWrapperFieldSetMapper<Customer> fieldSetterMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetterMapper.setTargetType(Customer.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetterMapper);

        return lineMapper;
    }

    //It sets up an ItemProcessor (CustomerItemProcessor) to process each Customer item.
    public CustomerItemProcessor processor(){
        return new CustomerItemProcessor();
    }


    //It configures a JdbcBatchItemWriter to write Customer objects to a database using JDBC.
    @Bean
    public JdbcBatchItemWriter<Customer> writer(){
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<>();
         writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
         writer.setSql("insert into customer(customerid,firstname,lastname) values (:customerid,:firstname,:lastname)");
         writer.setDataSource(this.dataSource);
         return writer;
    }

    //importCustomerJob(): Defines a Spring Batch Job named "Customer-Import-Job". It increments job parameters with each run (RunIdIncrementer), executes a single step (step1()), and completes the job.
    @Bean
    public Job importCustomerJob(){
        return this.jobBuilderFactory.get("Customer-Import-Job")
                .incrementer(new RunIdIncrementer())//new line means new row pa jana ka baad new id bun jaiyagi
                .flow(step1()) //step1 involves reading from a CSV file (reader), processing each line (processor), and writing to a database (writer).
                .end()
                .build();
    }

    @Bean
    public Step step1() {
       return  this.stepBuilderFactory.get("step1")
                .<Customer,Customer>chunk(10) // at each time how much data read process write -- here 10 row read process write at each time
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
