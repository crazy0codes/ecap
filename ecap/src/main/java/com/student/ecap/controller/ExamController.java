package com.student.ecap.controller;

import com.student.ecap.dto.ExamEventResponse;
import com.student.ecap.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for exam-related operations.
 */
@RestController
@RequestMapping("/api/exams") // Base path for exam-related endpoints
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from your frontend
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    /**
     * Endpoint to get all exam events for a given regulation and semester number.
     *
     * @param regulationId The ID of the regulation (e.g., "R18").
     * @param semesterNumber The number of the semester (e.g., 1).
     * @return ResponseEntity containing a list of ExamEventResponse DTOs.
     */
    @GetMapping("/schedule/{regulationId}/{semesterNumber}/events")
    public ResponseEntity<List<ExamEventResponse>> getExamEvents(
            @PathVariable String regulationId,
            @PathVariable Integer semesterNumber) {

        List<ExamEventResponse> events = examService.getExamEventsByRegulationAndSemester(regulationId, semesterNumber);

        if (events.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content if no events found
        }
        return ResponseEntity.ok(events); // HTTP 200 OK with the list of events
    }
}
