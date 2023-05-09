package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.*;

@SuppressWarnings("unused")
public class NoticeCredentials {
    @Lob
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 128)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
