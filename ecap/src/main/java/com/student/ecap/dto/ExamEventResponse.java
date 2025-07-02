package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for returning exam event details to the client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamEventResponse {
    private Integer id; // ExamEvent ID
    private Integer scheduleId; // Associated ExamSchedule ID
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}

