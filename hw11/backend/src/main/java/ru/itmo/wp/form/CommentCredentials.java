package ru.itmo.wp.form;

import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentCredentials {
    @NotBlank
    @Size(min = 1, max = 2048)
    @Lob
    private String text;

    private User user;

    private Post post;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
