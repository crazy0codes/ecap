package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents the 'exam_events' table in the database.
 * Stores specific exam events within an overall exam schedule.
 */
@Entity
@Table(name = "exam_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamEvent {

    /**
     * The unique event ID, which is the primary key and auto-incrementing.
     * Mapped to the 'id' column.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "id") // Maps to the column 'id'
    private Integer id;

    /**
     * The exam schedule this event belongs to.
     * Many-to-one relationship with the ExamSchedule entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id") // Defines the foreign key column
    private ExamSchedule schedule;

    /**
     * The start date of the exam event.
     * Mapped to the 'start_date' column.
     */
    @Column(name = "start_date") // Maps to the column 'start_date'
    private LocalDate startDate;

    /**
     * The end date of the exam event.
     * Mapped to the 'end_date' column.
     */
    @Column(name = "end_date") // Maps to the column 'end_date'
    private LocalDate endDate;

    /**
     * A description of the exam event (e.g., "Mid 1", "End Examinations").
     * Mapped to the 'description' column.
     */
    @Column(name = "description", length = 255) // Maps to the column 'description'
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExamSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(ExamSchedule schedule) {
        this.schedule = schedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
