package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import com.example.Blog.App.Api.Response.MeetingResponse;
import com.example.Blog.App.Api.Response.ScheduledResponse;
import com.example.Blog.App.Api.ServiceImpl.MeetingsService;
import com.example.Blog.App.Api.payload.meetingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeetingControllerTest {
    @Mock
    MeetingsService meetingsService;

    @InjectMocks
    MeetingController meetingController;
ModelMapper mapper;
MeetingPlatforms meetingPlatforms;
    @BeforeEach
    void setUp() {

    }

    @Test
    void getById() {
        meetingDto reponseMeetingDto = new meetingDto();
        ResponseEntity<meetingDto> meetingResponse = new ResponseEntity<>(reponseMeetingDto, HttpStatus.OK);
        when(meetingsService.getmeetingbyid(anyInt())).thenReturn(reponseMeetingDto);
        assertEquals(meetingResponse, meetingController.getById(1));
    }

    @Test
    void testCreateAmeeting() {
        // Arrange
        MeetingResponse expectedResponse = new MeetingResponse("Meeting created successfully");
        meetingDto inputMeetingDto = new meetingDto();
        doReturn(expectedResponse).when(meetingsService).createAmeeting(inputMeetingDto);

        // Act
        ResponseEntity<MeetingResponse> responseEntity = meetingController.createAmeeting(inputMeetingDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    @Test
    void testUpdatePost() {
        Integer id = 1;
        meetingDto postDto = new meetingDto();
        postDto.setId(id);
        // Set other properties of postDto as needed

        ScheduledResponse expectedResponse = new ScheduledResponse("Test");
        // Set properties of expectedResponse as needed

        when(meetingsService.updatepost(id, postDto)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ScheduledResponse> responseEntity = meetingController.updatePost(id, postDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
