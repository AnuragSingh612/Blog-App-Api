package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.SprintService;
import com.example.Blog.App.Api.payload.SprintDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SprintControlerTest {
    @Mock
    private SprintService sprintService;

    @InjectMocks
    private SprintControler sprintController;

    @Test
    public void testGetAllSprint() {
        // create a list of sprint DTOs
        List<SprintDto> sprintDtoList = new ArrayList<>();
        SprintDto sprintDto= new SprintDto();
        sprintDto.setSprintId(1);
        sprintDto.setSprintName("Sprint 1");
        sprintDto.setStartDate(LocalDateTime.now());
        sprintDto.setEndDate(LocalDateTime.now());
        // set up the mock behavior for the sprint service
        when(sprintService.getallSprint()).thenReturn(sprintDtoList);

        // call the method being tested
        ResponseEntity<List<SprintDto>> responseEntity = sprintController.getallSprint();

        // verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sprintDtoList, responseEntity.getBody());
    }
}