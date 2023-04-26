package com.example.Blog.App.Api.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SprintDto {
    private Integer sprintId;
    private String sprintName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer projectCode;
    private LocalDateTime createdOn=LocalDateTime.now();

}
