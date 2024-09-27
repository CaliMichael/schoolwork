package com.school.demo.config;

import com.school.demo.model.Employee;
import com.school.demo.processor.EmployeeProcessor;
import com.school.demo.writer.EmployeeWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    public JdbcCursorItemReader<Employee> reader;

    @Autowired
    public EmployeeProcessor processor;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public PlatformTransactionManager transactionManager;

    @Autowired
    public DataSource dataSource;

    @Value("${output.directory}")
    private String outputFilePath;

    @Bean
    public Job exportEmployeeJob() {
        return new JobBuilder("exportEmployeeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(exportEmployeeStep())
                .build();
    }


    @Bean
    public Step exportEmployeeStep() {
        return new StepBuilder("exportEmployeeStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager) // Adjust chunk size as needed
                .reader(reader)
                .processor(processor)
                .writer(writer())
                .build();
    }

    @Bean
    public EmployeeWriter writer() {
        return new EmployeeWriter(outputFilePath);
    }
}
