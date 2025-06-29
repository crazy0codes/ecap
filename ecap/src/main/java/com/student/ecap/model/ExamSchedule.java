package com.student.ecap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'exam_schedule' table in the database.
 * Stores general information about an exam schedule, linked to a semester and regulation.
 */
@Entity
@Table(name = "exam_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSchedule {

    /**
     * The unique schedule ID, which is the primary key and auto-incrementing.
     * Mapped to the 'id' column.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "id") // Maps to the column 'id'
    private Integer id;

    /**
     * The semester associated with this exam schedule.
     * Many-to-one relationship with the Semester entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id") // Defines the foreign key column
    private Semester semester;

    /**
     * The regulation associated with this exam schedule.
     * Many-to-one relationship with the Regulation entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_id", referencedColumnName = "regulation_id") // Defines the foreign key column
    private Regulation regulation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setRegulation(Regulation regulation) {
        this.regulation = regulation;
    }
}
