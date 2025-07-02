package com.student.ecap.service;

import com.student.ecap.dto.ExamEventResponse;
import com.student.ecap.model.ExamEvent;
import com.student.ecap.model.ExamSchedule;
import com.student.ecap.repository.ExamEventRepository;
import com.student.ecap.repository.ExamScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling exam-related business logic.
 */
@Service
public class ExamService {

    private final ExamScheduleRepository examScheduleRepository;
    private final ExamEventRepository examEventRepository;

    @Autowired
    public ExamService(ExamScheduleRepository examScheduleRepository, ExamEventRepository examEventRepository) {
        this.examScheduleRepository = examScheduleRepository;
        this.examEventRepository = examEventRepository;
    }

    /**
     * Retrieves all exam events for a specific semester and regulation.
     *
     * @param regulationId The ID of the regulation (e.g., "R18").
     * @param semesterNumber The number of the semester (e.g., 1).
     * @return A list of ExamEventResponse DTOs. Returns an empty list if no schedules or events are found.
     */
    @Transactional(readOnly = true) // Optimize for read operations
    public List<ExamEventResponse> getExamEventsByRegulationAndSemester(String regulationId, Integer semesterNumber) {
        // Find all exam schedules matching the regulation and semester
        List<ExamSchedule> schedules = examScheduleRepository.findBySemester_SemesterNumberAndRegulation_RegulationId(
                semesterNumber, regulationId
        );

        // Collect all exam events from the found schedules
        return schedules.stream()
                .flatMap(schedule -> examEventRepository.findBySchedule_Id(schedule.getId()).stream()) // Get events for each schedule
                .map(event -> new ExamEventResponse( // Map ExamEvent entity to ExamEventResponse DTO
                        event.getId(),
                        event.getSchedule().getId(), // Get the ID from the associated schedule
                        event.getStartDate(),
                        event.getEndDate(),
                        event.getDescription()
                ))
                .collect(Collectors.toList());
    }
}
