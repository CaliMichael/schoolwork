package com.school.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobErrorResponse {
    private String message;
    private String jobName;
    private String error;
}
