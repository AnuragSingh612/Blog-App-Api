package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.Entity.Category;
import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import com.example.Blog.App.Api.Entity.Meetings;
import com.example.Blog.App.Api.Entity.Sprints;
import com.example.Blog.App.Api.exception.MeetingStatusUpdateFailedException;
import com.example.Blog.App.Api.exception.ResourceNotfoundException;
import com.example.Blog.App.Api.payload.CategoryDto;
import com.example.Blog.App.Api.payload.meetingDto;
import com.example.Blog.App.Api.repository.MeetingPlatformRepo;
import com.example.Blog.App.Api.repository.MeetingsRepo;
import com.example.Blog.App.Api.repository.SprintRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingsService {
    @Autowired
    MeetingsRepo meetingsRepo;


    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SprintRepo sprintRepo;
    @Autowired
    MeetingPlatformRepo meetingPlatformsRepo;

    public meetingDto createAmeeting(meetingDto meetDto){

        String meetingType=meetDto.getMeetingType();
        Sprints s = sprintRepo.findById(meetDto.getSprintId()).orElseThrow(()-> new  ResourceNotfoundException("Sprint",meetDto.getSprintId(),"ID"));
        MeetingPlatforms meetingPlatforms=meetingPlatformsRepo.findById(meetDto.getMeeting_platform_id()).orElseThrow(()-> new  ResourceNotfoundException("Meeting Platfor",meetDto.getMeeting_platform_id(),"Id"));
        if(meetingType.equals("DailyScrum")){
            LocalDateTime startDate=s.getStartDate();
            LocalDateTime endDate=s.getEndDate();
            List<Meetings> meetingsList = new ArrayList<>(); // it is populated now
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                Meetings meeting = new Meetings();
                meeting.setMeetingLink(meetDto.getMeetingLink());
                meeting.setMeetingPassword(meetDto.getMeetingPassword());
                meeting.setMeetingType(meetingType);
                meeting.setCreatedOn(LocalDateTime.now());
                meeting.setMeetingTime(meetDto.getMeetingTime());
                meeting.setSprint(s);
                meeting.setMeetingPlatforms(meetingPlatforms);
                meeting.setUpdatedOn(LocalDateTime.now());
                meeting.setStatus(meetDto.getStatus());
                meetingsList.add(meeting);
            }
            meetingsRepo.saveAll(meetingsList);
            return new meetingDto();
        }
        else if(meetingType.equals("SprintPlanning")) {
            Meetings meeting= modelMapper.map(meetDto,Meetings.class);
            meeting.setMeetingDate(s.getStartDate());
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
            modelMapper.map(meeting, meetingDto.class);
            meetDto.setSprintId(s.getSprintId());
            meetDto.setMeeting_platform_id(meetingPlatforms.getId());
            meetDto.setUpdatedOn(LocalDateTime.now());
            meetDto.setId(meeting.getId());
            return meetDto;
        } else if (meetingType.equals("SprintRetrospective")) {
            Meetings meeting= modelMapper.map(meetDto,Meetings.class);
            meeting.setMeetingDate(s.getEndDate());
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
            modelMapper.map(meeting, meetingDto.class);
            meetDto.setSprintId(s.getSprintId());
            meetDto.setMeeting_platform_id(meetingPlatforms.getId());
            meetDto.setUpdatedOn(LocalDateTime.now());
            meetDto.setId(meeting.getId());
            return meetDto;
        } else {
            Meetings meeting= modelMapper.map(meetDto,Meetings.class);
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
            modelMapper.map(meeting, meetingDto.class);
            meetDto.setSprintId(s.getSprintId());
            meetDto.setMeeting_platform_id(meetingPlatforms.getId());
            meetDto.setUpdatedOn(LocalDateTime.now());
            meetDto.setId(meeting.getId());
            return meetDto;
        }
    }

    public meetingDto getMetingbyId(Integer id){
        Meetings meetings=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        return modelMapper.map(meetings,meetingDto.class);
    }

    public MeetingsService(MeetingsRepo meetingsRepo,ModelMapper modelMapper,MeetingPlatformRepo meetingPlatformsRepo) {
        this.meetingsRepo = meetingsRepo;
        this.modelMapper=modelMapper;
        this.meetingPlatformsRepo=meetingPlatformsRepo;
    }

    public meetingDto getmeetingbyid(Integer id) {
        Meetings meet=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        meetingDto m= new meetingDto();
        m.setStatus(meet.getStatus());
        m.setMeetingTime(meet.getMeetingTime());
        m.setId(meet.getId());
        m.setMeetingDate(meet.getMeetingDate());
        m.setMeetingLink(meet.getMeetingLink());
        m.setMeetingType(meet.getMeetingType());
        m.setMeetingPassword(meet.getMeetingPassword());
        m.setMeeting_platform_id(meet.getMeetingPlatforms().getId());
        m.setSprintId(meet.getSprint().getSprintId());
        m.setUpdatedOn(meet.getUpdatedOn());
        m.setCreatedOn(meet.getCreatedOn());
        return m;
    }

    public meetingDto updatepost(Integer id, meetingDto meetDto) {
        Meetings meet=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        meet.setId(id);
        meet.setMeetingLink(meetDto.getMeetingLink());
        meet.setMeetingDate(meetDto.getMeetingDate());
        meet.setMeetingTime(meetDto.getMeetingTime());
        meet.setMeetingType(meetDto.getMeetingType());
        meet.setUpdatedOn(LocalDateTime.now());
        System.out.println(meet.getMeetingLink());
        meetingsRepo.save(meet);
        modelMapper.map(meet,meetingDto.class);
        meetDto.setMeeting_platform_id(meet.getMeetingPlatforms().getId());
        return meetDto;
    }
}
