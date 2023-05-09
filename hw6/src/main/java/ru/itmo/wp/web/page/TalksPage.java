package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class TalksPage extends Page {
    private final TalkService talkService = new TalkService();

    private void checkLoggedUser() {
        if (getUser() == null) {
            setMessage("You should be logged in to use talks.");
            throw new RedirectException("/index");
        }

    }

    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        checkLoggedUser();
        view.put("users", userService.findAll());
        view.put("messages", talkService.findAllMessagesWithUser(getUser()));
        view.put("userService", userService);
    }

    private void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        action(request, view);
        User sourceUser = getUser();
        User targetUser = userService.findByLogin(request.getParameter("targetLogin"));
        String text = request.getParameter("text").trim();

        talkService.validateTalk(targetUser, text);

        Talk talk = new Talk();
        talk.setTargetUserId(targetUser.getId());
        talk.setSourceUserId(sourceUser.getId());
        talk.setText(text);

        talkService.saveTalk(talk);
        throw new RedirectException("/talks");
    }
}
