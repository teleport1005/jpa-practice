package com.example.jpapractice.article.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}

