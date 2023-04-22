package com.example.Blog.App.Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class MeetingStatusUpdateFailedException extends RuntimeException{
    private String meetingResource;

    public MeetingStatusUpdateFailedException(String meetingResource) {
        super(String.format("%s can't be rescheduled as it is expired", meetingResource));
        this.meetingResource = meetingResource;
    }

    public String getMeetingResource() {
        return meetingResource;
    }
}
