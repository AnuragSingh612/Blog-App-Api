package com.example.Blog.App.Api.ServiceImpl;
import com.example.Blog.App.Api.Entity.Sprints;
import com.example.Blog.App.Api.payload.SprintDto;
import com.example.Blog.App.Api.repository.SprintRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintService {
    public SprintService(SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    @Autowired
    SprintRepo sprintRepo;
    @Autowired
    ModelMapper modelMapper;
    public SprintDto addASprint(SprintDto sprintDto){
        Sprints sprint1=modelMapper.map(sprintDto, Sprints.class);
        Sprints sprints2=sprintRepo.save(sprint1);
        modelMapper.map(sprints2,SprintDto.class);
        sprintDto.setSprintId(sprints2.getSprintId());
        sprintDto.setCreatedOn(LocalDateTime.now());
        sprintDto.setSprintName(sprints2.getSprintName());
        return sprintDto;
    }

    public List<SprintDto> getallSprint() {
        List<Sprints> s=sprintRepo.findAll();
        return s.stream().map(sprints -> modelMapper.map(sprints,SprintDto.class)).collect(Collectors.toList());
    }
}
