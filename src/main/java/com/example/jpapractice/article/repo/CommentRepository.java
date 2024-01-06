package com.example.jpapractice.article.repo;

import com.example.jpapractice.article.entity.Article;
import com.example.jpapractice.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article entity);
    List<Comment> findByArticleId(Long id);
}
