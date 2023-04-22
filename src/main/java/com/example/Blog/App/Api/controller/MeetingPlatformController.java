package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.MeetingPlatformService;
import com.example.Blog.App.Api.payload.meetingplatformDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingPlatformController {
    @Autowired
    MeetingPlatformService meetingPlatformService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/platform")
    public ResponseEntity<List<meetingplatformDto>> getlistplatform(){
        return new ResponseEntity<>(meetingPlatformService.getallmeetingplatform(), HttpStatus.OK);
    }

}
