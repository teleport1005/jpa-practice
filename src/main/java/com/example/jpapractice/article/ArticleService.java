package com.example.jpapractice.article;

import com.example.jpapractice.article.entity.Article;
import com.example.jpapractice.article.entity.Comment;
import com.example.jpapractice.article.repo.ArticleRepository;
import com.example.jpapractice.article.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public void createArticle(
            String title,
            String content,
            String writer
    ) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setWriter(writer);
        articleRepository.save(article);
    }

    public List<Article> readArticleAll() {
        return articleRepository.findAll();
    }

    public Article readArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    public Comment readComment(Long article_id) {
       Optional<Comment> optionalComment = commentRepository.findById(article_id);
       return optionalComment.orElse(null);
    }

    public void updateArticle(
            Long id,
            String title,
            String content
    ) {
        Article target = articleRepository.findById(id).orElse(new Article());
        target.setTitle(title);
        target.setContent(content);
        articleRepository.save(target);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
