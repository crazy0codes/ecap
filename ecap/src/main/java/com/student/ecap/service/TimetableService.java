package com.student.ecap.service;

import com.student.ecap.dto.TimetableResponse;
import com.student.ecap.model.Timetable;
import com.student.ecap.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling timetable-related business logic.
 */
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    /**
     * Retrieves timetable entries for a specific branch, semester, and day.
     * @param branchId The ID of the branch.
     * @param semesterId The ID of the semester.
     * @param dayId The ID of the day.
     * @return A list of TimetableResponse DTOs.
     */
    @Transactional(readOnly = true)
    public List<TimetableResponse> getTimetable(Integer branchId, Integer semesterId, Integer dayId) {
        List<Timetable> timetables = timetableRepository.findByBranch_BranchIdAndSemester_SemesterIdAndDay_Id(branchId, semesterId, dayId);

        return timetables.stream()
                .map(entry -> new TimetableResponse(
                        entry.getId(),
                        entry.getBranch().getBranchName(),
                        entry.getSemester().getSemesterNumber(),
                        entry.getDay().getName(),
                        entry.getPeriod().getPeriodNumber(),
                        entry.getCourse().getCourseCode(),
                        entry.getCourse().getCourseName()
                ))
                .collect(Collectors.toList());
    }
}
    