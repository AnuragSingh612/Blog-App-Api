package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import com.example.Blog.App.Api.payload.meetingplatformDto;
import com.example.Blog.App.Api.repository.MeetingPlatformRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlatformServiceTest {

    @Mock
    private MeetingPlatformRepo meetingPlatformRepo;
    @Mock
    ModelMapper modelMapper;

    private MeetingPlatformService meetingPlatformService;

    @BeforeEach
    void setUp() {
        this.meetingPlatformService= new MeetingPlatformService(meetingPlatformRepo,modelMapper);
    }

    @Test
    void testGetAllMeetingPlatform() {
        // Arrange
        MeetingPlatforms meetingPlatform1 = new MeetingPlatforms();
        // Set properties of meetingPlatform1 as needed

        MeetingPlatforms meetingPlatform2 = new MeetingPlatforms();
        // Set properties of meetingPlatform2 as needed

        List<MeetingPlatforms> meetingPlatformsList = Arrays.asList(meetingPlatform1, meetingPlatform2);

        when(meetingPlatformRepo.findAll()).thenReturn(meetingPlatformsList);

        meetingplatformDto meetingPlatformDto1 = new meetingplatformDto();
        // Set properties of meetingPlatformDto1 as needed

        meetingplatformDto meetingPlatformDto2 = new meetingplatformDto();
        // Set properties of meetingPlatformDto2 as needed

        when(modelMapper.map(meetingPlatform1, meetingplatformDto.class)).thenReturn(meetingPlatformDto1);
        when(modelMapper.map(meetingPlatform2, meetingplatformDto.class)).thenReturn(meetingPlatformDto2);

        // Act
        List<meetingplatformDto> result = meetingPlatformService.getallmeetingplatform();

        // Assert
        assertEquals(2, result.size());
        assertEquals(meetingPlatformDto1, result.get(0));
        assertEquals(meetingPlatformDto2, result.get(1));
    }
    }

