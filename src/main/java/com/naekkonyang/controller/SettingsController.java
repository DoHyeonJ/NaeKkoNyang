package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.pet.PetForm;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final HttpSession httpSession;

    @GetMapping("/setting")
    public String settings(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        model.addAttribute("user", user);
        return "settings/setting-main";
    }
}
