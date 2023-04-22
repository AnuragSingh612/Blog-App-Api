package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.payload.SprintDto;
import com.example.Blog.App.Api.payload.meetingDto;
import com.example.Blog.App.Api.repository.MeetingsRepo;
import com.example.Blog.App.Api.repository.SprintRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class MeetingsServiceTest {
    @Mock
    MeetingsRepo meetingsRepo;
    @Mock
    SprintRepo sprintRepo;
    MeetingsService meetingsService;
    meetingDto m= new meetingDto();
    SprintDto sprintDto= new SprintDto();
    SprintService sprintService;
    @Test
    void createAmeeting() {
        sprintDto.setCreatedOn(LocalDateTime.now());
        sprintDto.setStartDate(LocalDateTime.now());
        sprintDto.setSprintName("Sprint002");
        sprintDto.setProjectCode("10001");
        sprintDto.setSprintId(2);
        sprintDto.setEndDate(LocalDateTime.now());
        m.setId(1);
        m.setCreatedOn(LocalDateTime.now());
        m.setMeeting_platform_id(1);
        m.setMeetingLink("testing");
        m.setMeetingTime(LocalDateTime.now());
        m.setMeetingPassword("password");
        m.setMeetingDate(LocalDateTime.now());
        m.setStatus("scheduled");
        m.setSprintId(2);
        m.setMeetingType("SprintReview");
        m.setUpdatedOn(LocalDateTime.now());
        meetingsService.createAmeeting(m);
        verify(meetingsRepo).findById(1);

    }

    @Test
    void getMetingbyId() {
    }

    @BeforeEach
    void setUp() {
        this.meetingsService= new MeetingsService(this.meetingsRepo);
        this.sprintService= new SprintService(this.sprintRepo);
    }

    @Test
    void reschedule() {
    }

    @Test
    void getmeetingbyid() {
    }

    @Test
    void updatepost() {
    }
}