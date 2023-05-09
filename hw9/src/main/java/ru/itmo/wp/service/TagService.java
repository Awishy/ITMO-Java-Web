package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.RoleRepository;
import ru.itmo.wp.repository.TagRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagService {

    /**
     * @noinspection FieldCanBeLocal, unused
     */
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public boolean existsByName(String name) {
        return tagRepository.existsByName(name);
    }
}
