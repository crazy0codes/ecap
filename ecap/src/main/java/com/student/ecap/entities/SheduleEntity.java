package com.student.ecap.entities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shedule")
public class SheduleEntity {

    @Id
    private String id;

    @NotNull(message = "Semester number is required")
    @Min(value = 1, message = "Semester number must be at least 1")
    private Integer semNo;

    @NotEmpty(message = "Schedule items must not be empty")
    @Valid
    private List<SheduleItem> items;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getSemNo() { return semNo; }
    public void setSemNo(Integer semNo) { this.semNo = semNo; }

    public List<SheduleItem> getItems() { return items; }
    public void setItems(List<SheduleItem> items) { this.items = items; }

    // Inner static class with validation
    public static class SheduleItem {

        @NotBlank(message = "Start date is required")
        private String startDate;

        @NotBlank(message = "End date is required")
        private String endDate;

        @NotBlank(message = "Description is required")
        private String description;

        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
