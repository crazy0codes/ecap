package com.student.ecap.dto;

public class LoginResponse {
    private String message;
    private boolean success;
    private String rollNumber;

    public LoginResponse() {}

    public LoginResponse(String message, boolean success, String rollNumber) {
        this.message = message;
        this.success = success;
        this.rollNumber = rollNumber;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
