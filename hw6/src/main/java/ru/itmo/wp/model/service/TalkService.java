package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();

    public void saveTalk(Talk talk) {
        talkRepository.save(talk);
    }

    public void validateTalk(User target, String text) throws ValidationException {
        if (target == null) {
            throw new ValidationException("Please choose target user.");
        }

        if (text.isEmpty()) {
            throw new ValidationException("Message shouldn't be empty");
        }
        if(text.length() > 1024) {
            throw new ValidationException("Message should be at most 1024 characters.");
        }
    }

    public List<Talk> findAllMessagesWithUser(User user) {
        return talkRepository.findAllMessagesWithUser(user);
    }
}
