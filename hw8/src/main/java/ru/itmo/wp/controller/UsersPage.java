package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String changeStatus(@RequestParam Map<String, String> allParams) {
        try {
            if (allParams.get("disable") != null) {
                userService.updateDisabled(Long.parseLong(allParams.get("disable")), true);
            } else if (allParams.get("enable") != null) {
                userService.updateDisabled(Long.parseLong(allParams.get("enable")), false);
            }
        } catch (NumberFormatException e) {
            // ignored
        }
        return "redirect:/users/all";
    }
}
