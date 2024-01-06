package com.example.jpapractice.article;

import com.example.jpapractice.article.entity.Article;
import com.example.jpapractice.article.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("create-view")
    public String articleCreate(Model model) {
        return "article/create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer
    ) {
        articleService.createArticle(title, content, writer);
        return "redirect:/article/home";
    }

    @GetMapping("/home")
    public String readAll(Model model) {
        model.addAttribute("article", articleService.readArticleAll());
        return "article/home";
    }

    @GetMapping("/read/{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        Article article = articleService.readArticle(id);
        model.addAttribute("article", article);
        List<Comment> comments = commentService.readCommentsByArticleId(id);
        model.addAttribute("comments", comments);
        return "article/read";
    }

    @PostMapping("/read/{id}")
    public String createComment(
            @PathVariable("id") Long id,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer
    ) {
        commentService.createComment(content, writer, id);
        return "redirect:/article/read/{id}";
    }

    @GetMapping("{id}/update-view")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.readArticle(id));
        return "article/update";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content
    ) {
        articleService.updateArticle(id, title, content);
        return String.format("redirect:/article/read/%d", id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/article/home";
    }
}
