package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {

        //현재는 Session에서 유저 정보를 가져옴 추후 변경예정
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 값이 있을때만 model 에 회원정보 담아주기
        if(user != null) {
            model.addAttribute("userName", user.getName()); //회원명
            model.addAttribute("userPicture", user.getPicture()); //구글프로필이미지
            model.addAttribute("userEmail", user.getEmail()); //회원이메일
        }
        return "index";
    }

}