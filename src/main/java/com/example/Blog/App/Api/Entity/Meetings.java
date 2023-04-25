package com.example.Blog.App.Api.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="meetings")
public class Meetings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String meetingLink;
    private LocalDateTime meetingDate;
    private LocalTime meetingTime;
    @Pattern(regexp= "^(DailyScrum|SprintPlanning|SprintReview|SprintRetrospective)$", message = "Invalid meeting type")
    private String meetingType;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sprint_id")
    Sprints sprint;

    private String meetingPassword;
    private LocalDateTime createdOn=LocalDateTime.now();
    @Pattern(regexp= "^(scheduled|completed|cancelled|rescheduled)$", message = "Invalid meeting status")
    private String status;
    private LocalDateTime updatedOn;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="meeting_platform_id")
    MeetingPlatforms meetingPlatforms;
}
