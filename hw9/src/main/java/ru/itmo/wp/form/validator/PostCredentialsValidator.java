package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.TagService;
import ru.itmo.wp.service.UserService;

import java.util.Locale;

@Component
public class PostCredentialsValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return PostCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostCredentials postForm = (PostCredentials) target;
            for (String stringTag : postForm.getTags().trim().split("\\s+")) {
                if (stringTag.length() > 32) {
                    errors.rejectValue("tags", "tags.length-exceed",
                            "Each tag should be at most 32 letters.");
                }
                if (!stringTag.matches("[a-z]*")) {
                        errors.rejectValue("tags", "tags.not-lowercase",
                                "Tags should be written in lowercase Latin letters");
                }
            }
        }
    }
}
