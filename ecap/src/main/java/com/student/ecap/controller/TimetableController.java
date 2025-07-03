package com.student.ecap.controller;

import com.student.ecap.dto.TimetableResponse;
import com.student.ecap.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for timetable-related operations.
 * All endpoints are publicly accessible as authentication is removed.
 */
@RestController
@RequestMapping("/api/timetable")
@CrossOrigin(origins = "*")
public class TimetableController {

    private final TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    /**
     * Endpoint to get the timetable for a specific branch, semester, and day.
     *
     * @param branchId The ID of the branch.
     * @param semesterId The ID of the semester.
     * @param dayId The ID of the day.
     * @return ResponseEntity containing a list of TimetableResponse DTOs.
     */
    @GetMapping("/{branchId}/{semesterId}/{dayId}")
    public ResponseEntity<List<TimetableResponse>> getTimetable(
            @PathVariable Integer branchId,
            @PathVariable Integer semesterId,
            @PathVariable Integer dayId) {

        List<TimetableResponse> timetableEntries = timetableService.getTimetable(branchId, semesterId, dayId);

        if (timetableEntries.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content if no entries found
        }
        return ResponseEntity.ok(timetableEntries); // HTTP 200 OK with the list of entries
    }
}
    