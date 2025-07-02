package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'branch' table in the database.
 * Stores information about academic branches (e.g., CSE, ECE).
 */
@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@AllArgsConstructor // This generates the (Integer branchId, String branchName) constructor
public class Branch {

    /**
     * The unique identifier for the branch.
     * Mapped to the 'branch_id' column, which is an auto-incrementing primary key.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "branch_id") // Maps to the column 'branch_id'
    private Integer branchId;

    /**
     * The name of the branch.
     * Mapped to the 'branch_name' column.
     */
    @Column(name = "branch_name", length = 255) // Maps to the column 'branch_name'
    private String branchName;

    // REMOVED: The manual constructor below, as @AllArgsConstructor handles it.
    // public Branch(Integer branchId, String branchName) {
    //     this.branchId = branchId;
    //     this.branchName = branchName;
    // }
}
