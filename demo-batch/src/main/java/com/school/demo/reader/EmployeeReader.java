package com.school.demo.reader;

import com.school.demo.model.Employee;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeReader extends JdbcCursorItemReader<Employee> {

    @Autowired
    public EmployeeReader(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT name, email, employee_code, address FROM employee"); // Adjust the query based on your table structure
        setRowMapper(new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setEmployeeCode(rs.getString("employee_code"));
                employee.setAddress(rs.getString("address"));
                return employee;
            }
        });
    }
}
