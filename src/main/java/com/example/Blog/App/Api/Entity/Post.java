package com.example.Blog.App.Api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="description",nullable = false)
    private String description;
    @Column(name="content",nullable = false)
    private String content;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    Category category;
}
//Well, here we have specified fetch type Lazy.
//It means whenever we load Post JPA entity, then the Category object won't load immediately.
//All right, we can get this Category object from Post entity object on demand just by calling the getter