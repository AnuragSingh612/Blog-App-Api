package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.MeetingsService;
import com.example.Blog.App.Api.payload.meetingDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    @Autowired
    MeetingsService meetingsService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<meetingDto> createAmeeting(@RequestBody meetingDto meetingDto){
        return new ResponseEntity<>(meetingsService.createAmeeting(meetingDto),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<meetingDto> getById(@PathVariable Integer id){
    return new ResponseEntity<>(meetingsService.getmeetingbyid(id),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<meetingDto> updatePost(@PathVariable(name="id") Integer id,@RequestBody meetingDto postDto)
    {
        return new ResponseEntity<>(meetingsService.updatepost(id,postDto),HttpStatus.OK);
    }
}
