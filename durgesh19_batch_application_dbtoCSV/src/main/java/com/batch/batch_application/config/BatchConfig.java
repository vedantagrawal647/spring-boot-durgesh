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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
                     

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

    //reader
    /*
    Defines a method databaseReader() that configures
    a JdbcCursorItemReader. This reader retrieves data
    from the database (dataSource) using the SQL query
    specified (SELECT customerid, firstname, lastname
    FROM customer). It maps each row fetched from the
    database to a Customer object using BeanPropertyRowMapper.
     */
    public JdbcCursorItemReader<Customer> databaseReader() {
        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT customerid, firstname, lastname FROM customer"); // Query to fetch data from database
        reader.setRowMapper(new BeanPropertyRowMapper<>(Customer.class));
        return reader;
    }

    /*
    Defines a method csvWriter() that configures a
    FlatFileItemWriter.This writer writes data to
    a CSV file located at C://Users//vedant.agrawal_spice//Downloads/customer.csv.
    It specifies the fields (customerid, firstname, lastname) to include in each line
    of the CSV file and uses a comma , as the delimiter between fields.
     */

    public FlatFileItemWriter<Customer> csvWriter() {
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("C://Users//vedant.agrawal_spice//Downloads/customer.csv")); // Specify the output file path

        DelimitedLineAggregator<Customer> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"customerid", "firstname", "lastname"}); // Specify the fields to include in the CSV

        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);

        return writer;
    }

    /*
    Defines a Spring Batch job named Customer-Export-Job
    using JobBuilderFactory. It increments the job instance
     id with RunIdIncrementer, ensuring each execution gets
     a unique identifier. The job includes a single step (step1()).
     */
    @Bean
    public Job exportCustomerJob() {
        return jobBuilderFactory.get("Customer-Export-Job")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    /*
    ines a step named step1 within the Spring
     Batch job configuration. This step processes
      data in chunks of 10 (chunk(10)), reading
       data using databaseReader() and writing it
        to a CSV file using csvWriter().
     */

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(databaseReader())
                .writer(csvWriter())
                .build();
    }

}
