package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.MeetingPlatformService;
import com.example.Blog.App.Api.payload.meetingplatformDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetingPlatformControllerTest {
    @Mock
    private MeetingPlatformService meetingPlatformService;

    @InjectMocks
    private MeetingPlatformController meetingPlatformController;

    @Test
    public void testGetListPlatform() {
        // create a list of meeting platform DTOs
        List<meetingplatformDto> meetingPlatformDtoList = new ArrayList<>();
        meetingplatformDto dto= new meetingplatformDto();
        dto.setName("Zoom");
        meetingPlatformDtoList.add(dto);
        meetingPlatformDtoList.add(dto);

        // set up the mock behavior for the meeting platform service
        when(meetingPlatformService.getallmeetingplatform()).thenReturn(meetingPlatformDtoList);

        // call the method being tested
        ResponseEntity<List<meetingplatformDto>> responseEntity = meetingPlatformController.getlistplatform();

        // verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(meetingPlatformDtoList, responseEntity.getBody());
    }

}