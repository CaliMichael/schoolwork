package com.school.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobSuccessResponse {
    private String message;
    private String jobName;
    private Long jobId;
    private String status;
}
