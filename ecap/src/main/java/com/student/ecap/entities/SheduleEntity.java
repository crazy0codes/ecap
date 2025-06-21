package com.student.ecap.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "shedule")
public class SheduleEntity {

    @Id
    private String id;
    private int semNo;
    private List<SheduleItem> items;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getSemNo() { return semNo; }
    public void setSemNo(int semNo) { this.semNo = semNo; }

    public List<SheduleItem> getItems() { return items; }
    public void setItems(List<SheduleItem> items) { this.items = items; }

    public static class SheduleItem
    {
        private String startDate;
        private String endDate;
        private String description;

        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
