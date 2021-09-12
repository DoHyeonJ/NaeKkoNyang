package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.diary.Diary;
import com.naekkonyang.domain.diary.DiaryService;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final PetService petService;
    private final DiaryService diaryService;

    @GetMapping("/")
    public String index(Model model, Account account) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 값이 있을때만 model 에 회원정보 담아주기
        if(user != null) {
            model.addAttribute("userName", user.getName()); //회원명
            account.setId(user.getId());

            List<Pet> petList = petService.getPetList(account);
            List<Pet> diaryCompleteList = new ArrayList<>();

            // 펫과 함께한 날짜(일수)를 계산하여 넣어준다.
            for (Pet pet : petList) {
                    String day = pet.getPet_birth();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일", Locale.US);
                    LocalDate date = LocalDate.parse(day, formatter); //펫의 생일
                    LocalDate thisDate = LocalDate.now(); //현재날짜
                    Long petDate = ChronoUnit.DAYS.between(date, thisDate); //차이
                    pet.setPet_days(petDate+2);
                if(diaryService.checkDiaryComplete(pet)) { //수정해야될 일기가있다
                    diaryCompleteList.add(pet);
                }
            }
            model.addAttribute("diaryCompleteList", diaryCompleteList);
            model.addAttribute("petList", petList);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}