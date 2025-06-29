package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'regulation' table in the database.
 * Stores information about academic regulations.
 */
@Entity
@Table(name = "regulation")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class Regulation {

    /**
     * The unique identifier for the regulation.
     * Mapped to the 'regulation_id' column.
     */
    @Id // Marks this field as the primary key
    @Column(name = "regulation_id", length = 255) // Maps to the column 'regulation_id', specifying varchar length
    private String regulationId;

    // regulation_code is commented out in DBML, so not included here.

    public String getRegulationId() {
        return regulationId;
    }

    public void setRegulationId(String regulationId) {
        this.regulationId = regulationId;
    }
}
