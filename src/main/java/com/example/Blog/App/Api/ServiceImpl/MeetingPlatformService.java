package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import com.example.Blog.App.Api.Entity.Meetings;
import com.example.Blog.App.Api.controller.MeetingPlatformController;
import com.example.Blog.App.Api.payload.CategoryDto;
import com.example.Blog.App.Api.payload.meetingplatformDto;
import com.example.Blog.App.Api.repository.MeetingPlatformRepo;
import com.example.Blog.App.Api.repository.MeetingsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MeetingPlatformService {
    @Autowired
    MeetingPlatformRepo meetingPlatformRepo;
    @Autowired
    ModelMapper modelMapper;
    public List<meetingplatformDto> getallmeetingplatform(){
        List<MeetingPlatforms> meetingsPlatformList= meetingPlatformRepo.findAll();
        return meetingsPlatformList.stream().map(meetings -> modelMapper.map(meetings, meetingplatformDto.class)).collect(Collectors.toList());
    }

    public MeetingPlatformService(MeetingPlatformRepo meetingPlatformRepo, ModelMapper modelMapper) {
        this.meetingPlatformRepo = meetingPlatformRepo;
        this.modelMapper = modelMapper;
    }
}
