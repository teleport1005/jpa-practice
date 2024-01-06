package com.example.jpapractice.article;

import com.example.jpapractice.article.entity.Comment;
import com.example.jpapractice.article.repo.ArticleRepository;
import com.example.jpapractice.article.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public void createComment(
            String content,
            String writer,
            Long articleId
    ) {
        Comment commentCreate = new Comment();
        commentCreate.setContent(content);
        commentCreate.setWriter(writer);
        commentCreate.setArticle(articleRepository.findById(articleId).orElse(null));
        commentRepository.save(commentCreate);
    }

    public List<Comment> readCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public void updateComment(
            Long id,
            String content,
            String writer,
            Long articleId
    ) {
        Comment target = commentRepository.findById(id).orElse(null);
        assert target != null;
        target.setContent(content);
        target.setWriter(writer);
        commentRepository.save(target);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
