package com.example.Blog.App.Api.Entity;

import com.example.Blog.App.Api.SprintNameGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Sprints")
@EntityListeners(SprintNameGenerator.class)
public class Sprints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sprintId;

    private String sprintName;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endDate;

    private String projectCode;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate createdOn=LocalDate.now();
    @AssertTrue(message = "End date must be after start date")
    private boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null) {
            return true; // Ignore validation if either date is null
        }
        return endDate.isAfter(startDate);
    }

}
