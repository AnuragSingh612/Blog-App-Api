package com.example.Blog.App.Api.ServiceImpl;


import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import com.example.Blog.App.Api.Entity.Meetings;
import com.example.Blog.App.Api.Entity.Sprints;
import com.example.Blog.App.Api.payload.meetingDto;
import com.example.Blog.App.Api.repository.MeetingPlatformRepo;
import com.example.Blog.App.Api.repository.MeetingsRepo;
import com.example.Blog.App.Api.repository.SprintRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MeetingsServiceTest {
//
//    SprintRepo sprintRepo = Mockito.mock(SprintRepo.class);
//
    @Mock
    MeetingPlatformRepo meetingPlatformsRepo;
//
    @Mock
    MeetingsRepo meetingsRepo;
//
    @Mock
    ModelMapper modelMapper;
//

    MeetingsService meetingsService;
//
//
//
//    @Test
//    void createAMeeting() {
//        meetingDto meetingDtoDailyScrum = new meetingDto();
//        meetingDtoDailyScrum.setMeetingLink("meetingLink");
//        meetingDtoDailyScrum.setMeetingPassword("meetingPassword");
//        meetingDtoDailyScrum.setMeetingType("DailyScrum"); // it is for Daily Scrum
//        meetingDtoDailyScrum.setMeetingTime(LocalTime.now());
//        meetingDtoDailyScrum.setStatus("status");
//
//        Sprints responseSprint = new Sprints();
//        responseSprint.setStartDate(LocalDateTime.now());
//        responseSprint.setEndDate(LocalDateTime.now());
//
//        MeetingPlatforms responseMeetingPlatforms = new MeetingPlatforms();
//
//        List<Meetings> meetingsList = new ArrayList<>();
//
//        Meetings meeting = new Meetings();
//
//        when(sprintRepo.findById(anyInt())).thenReturn(Optional.of(responseSprint));
//        when(meetingPlatformsRepo.findById(anyInt())).thenReturn(Optional.of(responseMeetingPlatforms));
//        when(meetingsRepo.saveAll(anyList())).thenReturn(meetingsList);
//        when(meetingsRepo.save(any(Meetings.class))).thenReturn(meeting);
//        when(modelMapper.map(any(meetingDto.class),any())).thenReturn(meeting);
//
//
//        meetingsService.createAmeeting(meetingDtoDailyScrum);
////        meetingDtoDailyScrum.setMeetingType("SprintPlanning");
////        verify(meetingsService).createAmeeting(meetingDtoDailyScrum);
////        meetingDtoDailyScrum.setMeetingType("SprintRetrospective");
////        verify(meetingsService).createAmeeting(meetingDtoDailyScrum);
////        meetingDtoDailyScrum.setMeetingType("Test");
////        verify(meetingsService).createAmeeting(meetingDtoDailyScrum);
//        verify(sprintRepo).findById(1);
//        verify(meetingPlatformsRepo).findById(1);
//        verify(meetingsRepo).saveAll(meetingsList);

    @BeforeEach
    void setUp() {
        this.meetingsService= new MeetingsService(this.meetingsRepo,this.modelMapper,this.meetingPlatformsRepo);
    }

    @Test
    void testGetMeetingById() {
        // Arrange
        Integer id = 1;
        Meetings meeting = new Meetings();
        meeting.setId(id);
        meeting.setMeetingType("SprintReview");
        when(meetingsRepo.findById(id)).thenReturn(Optional.of(meeting));

        // Act
        meetingDto result = meetingsService.getMetingbyId(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("SprintReview", result.getMeetingType());
    }


}
