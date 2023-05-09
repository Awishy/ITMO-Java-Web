package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCredentials {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    private String text;

    @NotNull
    @Size(max = 255)
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
