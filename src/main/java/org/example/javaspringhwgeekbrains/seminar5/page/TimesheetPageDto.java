package org.example.javaspringhwgeekbrains.seminar5.page;

import lombok.Data;

@Data
public class TimesheetPageDto {

    private String projectName;
    private String id;
    private String employeeId;
    private String projectId;
    private String minutes;
    private String createdAt;
}
