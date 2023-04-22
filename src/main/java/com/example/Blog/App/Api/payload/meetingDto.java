package com.example.Blog.App.Api.payload;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class meetingDto {
    private Integer Id;
    private String meetingLink;
    private LocalDateTime meetingDate;
    private LocalDateTime meetingTime;
    private Integer sprintId;
    private Integer meeting_platform_id;
    private String meetingType;
    private String meetingPassword;
    private LocalDateTime createdOn=LocalDateTime.now();
    private String status;
    private LocalDateTime updatedOn;
}
