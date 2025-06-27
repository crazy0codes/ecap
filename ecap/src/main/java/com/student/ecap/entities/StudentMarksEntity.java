package com.student.ecap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

@Entity
@Table(name = "student_marks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rollno", "semNo"})
})
public class StudentMarksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Roll number is required")
    @Column(nullable = false)
    private String rollno;

    @NotNull(message = "Semester number is required")
    @Column(nullable = false)
    private Integer semNo;

    @NotBlank(message = "Regulation is required")
    @Column(nullable = false)
    private String regulation;

    @ElementCollection
    @CollectionTable(name = "marks_mid1", joinColumns = @JoinColumn(name = "student_marks_id"))
    @MapKeyColumn(name = "subject")
    @Column(name = "mark")
    private Map<String, Integer> mid1;

    @ElementCollection
    @CollectionTable(name = "marks_mid2", joinColumns = @JoinColumn(name = "student_marks_id"))
    @MapKeyColumn(name = "subject")
    @Column(name = "mark")
    private Map<String, Integer> mid2;

    @ElementCollection
    @CollectionTable(name = "marks_sem", joinColumns = @JoinColumn(name = "student_marks_id"))
    @MapKeyColumn(name = "subject")
    @Column(name = "mark")
    private Map<String, Integer> semMarks;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public Integer getSemNo() { return semNo; }
    public void setSemNo(Integer semNo) { this.semNo = semNo; }

    public String getRegulation() { return regulation; }
    public void setRegulation(String regulation) { this.regulation = regulation; }

    public Map<String, Integer> getMid1() { return mid1; }
    public void setMid1(Map<String, Integer> mid1) { this.mid1 = mid1; }

    public Map<String, Integer> getMid2() { return mid2; }
    public void setMid2(Map<String, Integer> mid2) { this.mid2 = mid2; }

    public Map<String, Integer> getSemMarks() { return semMarks; }
    public void setSemMarks(Map<String, Integer> semMarks) { this.semMarks = semMarks; }
}
