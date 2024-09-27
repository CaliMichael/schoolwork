package com.school.demo.writer;

import com.school.demo.model.BatchConstants;
import com.school.demo.model.Employee;
import com.school.demo.util.DateTimeUtil;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWriter extends FlatFileItemWriter<Employee> {

    private String outputFilePath;

    public EmployeeWriter(@Value("${output.directory}") String outputDirectory) {
        this.outputFilePath = outputDirectory;
        setLineAggregator(new LineAggregator<Employee>() {
            @Override
            public String aggregate(Employee employee) {
                return employee.getName() + "," + employee.getEmployeeCode() + "," + employee.getEmail() + "," + employee.getAddress(); // Customize CSV format
            }
        });
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // Append timestamp to the file name to ensure uniqueness for each run
        String filePathWithTimestamp = outputFilePath + BatchConstants.EMPLOYEE_LIST_CSV_FILE_PREFIX
                + DateTimeUtil.getCurrentTimeStamp()
                + BatchConstants.CSV_FILE_EXTENSION;

        // Set the resource to ensure a new file is created
        setResource(new FileSystemResource(filePathWithTimestamp));

        System.out.println("Generating file: " + filePathWithTimestamp);

        super.open(executionContext);
    }
}
