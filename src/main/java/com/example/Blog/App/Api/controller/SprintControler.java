package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.SprintService;
import com.example.Blog.App.Api.payload.SprintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintControler {
    @Autowired
    SprintService sprintService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/new")
    public ResponseEntity<SprintDto> addSprint(@RequestBody SprintDto sprintDto){
        return new ResponseEntity<>(sprintService.addASprint(sprintDto),HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<SprintDto>> getallSprint(){
        return new ResponseEntity<List<SprintDto>>(sprintService.getallSprint(),HttpStatus.OK);
    }
}
