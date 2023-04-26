package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.Response.ScheduledResponse;
import com.example.Blog.App.Api.ServiceImpl.MeetingsService;
import com.example.Blog.App.Api.payload.meetingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MeetingControllerTest {
    @Mock
    MeetingsService meetingsService;

    @InjectMocks
    MeetingController meetingController;

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
        meetingDto meetingDto = new meetingDto(/* set meetingDto properties */);

        // Act
        meetingController.createAmeeting(meetingDto);

        // Assert
        verify(meetingsService).createAmeeting(meetingDto);
    }
    @Test
    void testUpdatePost() {
        // Arrange
        Integer id = 1;
        meetingDto meetingDto = new meetingDto(/* set meetingDto properties */);
        ScheduledResponse sr= new ScheduledResponse("success");
        when(meetingsService.updatepost(id, meetingDto)).thenReturn(sr);

        // Act
        ResponseEntity<ScheduledResponse> responseEntity = meetingController.updatePost(id, meetingDto);

        // Assert
        verify(meetingsService).updatepost(id, meetingDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(meetingDto, responseEntity.getBody());
    }
}