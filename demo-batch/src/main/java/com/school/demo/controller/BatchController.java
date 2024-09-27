package com.school.demo.controller;
import com.school.demo.model.response.JobErrorResponse;
import com.school.demo.model.response.JobSuccessResponse;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportEmployeeJob;

    @PostMapping("/run-job")
    public ResponseEntity<?> startJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(exportEmployeeJob, params);

            // Creating and returning success DTO
            JobSuccessResponse successResponse = new JobSuccessResponse(
                    "Job started successfully",
                    exportEmployeeJob.getName(),
                    jobExecution.getJobId(),
                    jobExecution.getStatus().toString()
            );
            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            // Creating and returning error DTO
            JobErrorResponse errorResponse = new JobErrorResponse(
                    "Failed to start job",
                    exportEmployeeJob.getName(),
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
