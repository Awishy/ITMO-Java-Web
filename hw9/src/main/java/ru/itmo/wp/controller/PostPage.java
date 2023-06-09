package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping({"/post/{id}", "/post"})
    public String user(@PathVariable(required = false) String id, Model model) {
        try {
            model.addAttribute("post", postService.findById(Long.parseLong(id)));
            model.addAttribute("comment", new Comment());
        } catch (NumberFormatException e) {
            // ignored
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writePostPost(
            @PathVariable(required = false) String id,
            Model model,
            @Valid @ModelAttribute("comment") Comment comment,
            BindingResult bindingResult,
            HttpSession httpSession
    ) {
        try {
            model.addAttribute("post", postService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            // ignored
        }
        if (bindingResult.hasErrors()) {
            return "PostPage";
        }
        Post post = postService.findById(Long.parseLong(id));
        if (post != null) {
            postService.writeComment(post, comment, getUser(httpSession));
            putMessage(httpSession, "Comment sent");
        }
        return "redirect:/post/" + id;
    }
}
