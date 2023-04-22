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

    public MeetingsService(MeetingsRepo meetingsRepo) {
        this.meetingsRepo = meetingsRepo;
    }

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SprintRepo sprintRepo;
    @Autowired
    MeetingPlatformRepo meetingPlatformsRepo;
    public void createAmeeting(meetingDto meetingDto){

        String meetingType=meetingDto.getMeetingType();
        Sprints s = sprintRepo.findById(meetingDto.getSprintId()).orElseThrow(()-> new  ResourceNotfoundException("Sprint",meetingDto.getSprintId(),"ID"));
        MeetingPlatforms meetingPlatforms=meetingPlatformsRepo.findById(meetingDto.getMeeting_platform_id()).orElseThrow(()-> new  ResourceNotfoundException("Meeting Platfor",meetingDto.getMeeting_platform_id(),"Id"));
        if(meetingType.equals("DailyScrum")){
            LocalDateTime startDate=s.getStartDate();
            LocalDateTime endDate=s.getEndDate();
            List<Meetings> meetingsList = new ArrayList<>(); // it is populated now
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                Meetings meeting = new Meetings();
                meeting.setMeetingLink(meetingDto.getMeetingLink());
                meeting.setMeetingPassword(meetingDto.getMeetingPassword());
                meeting.setMeetingType(meetingType);
                meeting.setCreatedOn(LocalDateTime.now());
                meeting.setMeetingTime(meetingDto.getMeetingTime());
                meeting.setSprint(s);
                meeting.setMeetingPlatforms(meetingPlatforms);
                meeting.setUpdatedOn(LocalDateTime.now());
                meeting.setStatus(meetingDto.getStatus());
                meetingsList.add(meeting);
            }
            meetingsRepo.saveAll(meetingsList);
        }
        else if(meetingType.equals("SprintPlanning")) {
            Meetings meeting= modelMapper.map(meetingDto,Meetings.class);
            meeting.setMeetingDate(s.getStartDate());
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
        } else if (meetingType.equals("SprintRetrospective")) {
            Meetings meeting= modelMapper.map(meetingDto,Meetings.class);
            meeting.setMeetingDate(s.getEndDate());
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
        } else {
            Meetings meeting= modelMapper.map(meetingDto,Meetings.class);
            meeting.setMeetingPlatforms(meetingPlatforms);
            meeting.setUpdatedOn(LocalDateTime.now());
            meetingsRepo.save(meeting);
        }
    }

    public meetingDto getMetingbyId(Integer id){
        Meetings meetings=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        return modelMapper.map(meetings,meetingDto.class);
    }


    public void reschedule(Integer id, meetingDto meetingDto) throws MeetingStatusUpdateFailedException {
        Meetings meeting = meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        if(meeting.getMeetingDate().isBefore(LocalDateTime.now())) {
            throw new MeetingStatusUpdateFailedException(meeting.getMeetingLink());
        }
        meeting.setMeetingDate(meetingDto.getMeetingDate());
        meeting.setMeetingTime(meetingDto.getMeetingTime());
        meeting.setStatus(meetingDto.getStatus());
        meetingsRepo.save(meeting);
    }

    public meetingDto getmeetingbyid(Integer id) {
        Meetings meet=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        return modelMapper.map(meet, meetingDto.class);
    }

    public meetingDto updatepost(Integer id, meetingDto meetDto) {
        Meetings meet=meetingsRepo.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        meet.setId(id);
        meet.setMeetingLink(meetDto.getMeetingLink());
        meet.setMeetingDate(meetDto.getMeetingDate());
        meet.setMeetingTime(meetDto.getMeetingTime());
        System.out.println(meet.getMeetingLink());
        meetingsRepo.save(meet);
        return modelMapper.map(meet,meetingDto.class);
    }
}
