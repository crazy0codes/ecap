package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for creating a new attendance session.
 * A teacher would use this to initiate attendance for a class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSessionCreateRequest {
    private Integer teacherId;
    private Integer courseId;
    private Integer branchId;
    private Integer semesterId;
    private Integer periodId;
    private Integer dayId;
    private LocalDate date; // The actual calendar date of the session
}
