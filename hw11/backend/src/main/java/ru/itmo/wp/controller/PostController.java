package ru.itmo.wp.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @GetMapping({"/posts/{id}"})
    public Post findPost(@PathVariable(required = false) String id) {
        return postService.findById(Long.parseLong(id));
    }

    @PostMapping("posts/writePost")
    public void writePost(@RequestBody @Valid Post post,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        postService.save(post);
    }

    @PostMapping("posts/writeComment")
    public void writePost(@RequestBody @Valid CommentCredentials commentCredentials,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        commentService.save(commentCredentials);
    }
}
