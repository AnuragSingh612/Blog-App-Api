package com.example.Blog.App.Api.repository;

import com.example.Blog.App.Api.Entity.MeetingPlatforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface MeetingPlatformRepo extends JpaRepository<MeetingPlatforms,Integer> {
}
