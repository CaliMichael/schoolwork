package com.school.demo.writer;

import com.school.demo.model.BatchConstants;
import com.school.demo.model.Employee;
import com.school.demo.util.DateTimeUtil;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWriter extends FlatFileItemWriter<Employee> {

    public EmployeeWriter(@Value("${output.directory}")  String outputFilePath) {
        setResource(new FileSystemResource(outputFilePath+BatchConstants.EMPLOYEE_LIST_CSV_FILE_PREFIX+ DateTimeUtil.getCurrentTimeStamp()+ BatchConstants.CSV_FILE_EXTENSION)); // Adjust the path as needed
        setLineAggregator(new LineAggregator<Employee>() {
            @Override
            public String aggregate(Employee employee) {
                return employee.getName() + "," + employee.getEmployeeCode()+","+employee.getEmail()+","+employee.getAddress(); // Customize CSV format
            }
        });
    }

    @Override
    public String doWrite(Chunk<? extends Employee> items) {
        return super.doWrite(items);
    }
}
