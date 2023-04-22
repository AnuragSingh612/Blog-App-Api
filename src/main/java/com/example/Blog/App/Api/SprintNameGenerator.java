package com.example.Blog.App.Api;

import com.example.Blog.App.Api.Entity.Sprints;
import jakarta.persistence.PrePersist;

public class SprintNameGenerator {
    private static final String PREFIX = "Sprint-";
    private static final int LENGTH = 3;
    private static int count = 0;

    @PrePersist
    public void generateSprintName(Sprints sprint) {
        String formattedCount = String.format("%0" + LENGTH + "d", count++);
        sprint.setSprintName(PREFIX + formattedCount);
    }
}

