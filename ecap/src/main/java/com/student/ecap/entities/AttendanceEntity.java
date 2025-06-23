package com.student.ecap.entities;



import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="attendance")
public class AttendanceEntity {
    @Id
//    private ObjectId userId;
    private String userId;
    @NotBlank
    private Integer working;
    @NotBlank
    private Integer present;

    public AttendanceEntity(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getWorking() {
        return working;
    }

    public void setWorking(Integer working) {
        this.working = working;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

}
