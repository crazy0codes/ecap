package com.student.ecap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'course_offering' table in the database.
 * Links regulations, branches, semesters to courses offered in a specific context.
 * The 'courses' array field is handled as a JSON string for simplicity,
 * assuming it stores a list of course IDs. For a more robust solution,
 * a many-to-many relationship with a join table would typically be used.
 */
@Entity
@Table(name = "course_offering")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseOffering {

    /**
     * The unique identifier for the course offering.
     * Mapped to the 'offering_id' column, which is an auto-incrementing primary key.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "offering_id") // Maps to the column 'offering_id'
    private Integer offeringId;

    /**
     * The regulation associated with this offering.
     * Many-to-one relationship with the Regulation entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Many course offerings can belong to one regulation
    @JoinColumn(name = "regulation_id", referencedColumnName = "regulation_id") // Defines the foreign key column
    private Regulation regulation;

    /**
     * The branch associated with this offering.
     * Many-to-one relationship with the Branch entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Many course offerings can belong to one branch
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id") // Defines the foreign key column
    private Branch branch;

    /**
     * The semester associated with this offering.
     * Many-to-one relationship with the Semester entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Many course offerings can belong to one semester
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id") // Defines the foreign key column
    private Semester semester;

    /**
     * An array of courses offered in this context.
     * Stored as a JSON string of course IDs.
     * In a real-world scenario, this might be a @ManyToMany relationship with a join table.
     */
    @Column(name = "courses", columnDefinition = "TEXT") // Mapped as TEXT to store JSON string
    private String courses; // Example: "[1, 2, 3]" or "['CS101', 'MA202']"

    public Integer getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Integer offeringId) {
        this.offeringId = offeringId;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setRegulation(Regulation regulation) {
        this.regulation = regulation;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
}

