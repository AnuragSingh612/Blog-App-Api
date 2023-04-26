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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        List<MeetingPlatforms> meetingPlatforms = new ArrayList<>();
        MeetingPlatforms mp1 = new MeetingPlatforms();
        mp1.setId(1);
        mp1.setName("Zoom");
        meetingPlatforms.add(mp1);
        MeetingPlatforms mp2 = new MeetingPlatforms();
        mp2.setId(2);
        mp2.setName("Microsoft Teams");
        meetingPlatforms.add(mp2);

        Mockito.when(meetingPlatformRepo.findAll()).thenReturn(meetingPlatforms);

        // Act
        List<meetingplatformDto> meetingPlatformDtos = meetingPlatformService.getallmeetingplatform();

        // Assert
        assertNotNull(meetingPlatformDtos);
        assertEquals(meetingPlatforms.size(), meetingPlatformDtos.size());
        assertEquals(mp1.getName(), meetingPlatformDtos.get(0).getName());
        assertEquals(mp2.getName(), meetingPlatformDtos.get(1).getName());
    }
}
