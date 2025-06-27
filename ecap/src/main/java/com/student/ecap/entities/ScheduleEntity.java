package com.student.ecap.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam_schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Semester number is required")
    @Min(value = 1, message = "Semester number must be at least 1")
    @Column(name = "semester_id")
    private Integer semNo;

    @ElementCollection
    @CollectionTable(name = "exam_events", joinColumns = @JoinColumn(name = "schedule_id"))
    @Valid
    private List<ScheduleItem> items = new ArrayList<>();

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSemNo() { return semNo; }
    public void setSemNo(Integer semNo) { this.semNo = semNo; }

    public List<ScheduleItem> getItems() { return items; }
    public void setItems(List<ScheduleItem> items) { this.items = items; }

    @Embeddable
    public static class ScheduleItem {

        @NotBlank(message = "Start date is required")
        @Column(name = "start_date")
        private String startDate;

        @NotBlank(message = "End date is required")
        @Column(name = "end_date")
        private String endDate;

        @NotBlank(message = "Description is required")
        @Column(name = "description")
        private String description;

        // Getters and setters
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
