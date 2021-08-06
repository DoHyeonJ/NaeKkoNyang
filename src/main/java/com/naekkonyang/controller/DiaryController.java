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
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final HttpSession httpSession;
    private final PetService petService;

    @GetMapping("/diary-list/{id}")
    public String diaryList(Model model, @PathVariable("id") Pet pet, Account account) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        account.setId(user.getId());

        // 펫 권한여부 조회 권한없을시 리스트로 리다이렉트
        if(petService.checkAccount(account, pet)) { return "redirect:/pet-list";}

        model.addAttribute("diaryList", diaryService.getDiaryList(pet));
        return "diary/diary-list";
    }

    @GetMapping("/diary-registration")
    public String diaryRegistration() {
        return "diary/diary-registration";
    }

    @GetMapping("/diary-detail/{id}")
    public String diaryDetail(@PathVariable("id") Diary diary, Model model) {
        if (diaryService.checkAccount(diary)) {
            return "redirect:/diary-petList";
        }

        model.addAttribute("diary", diary);
        return "diary/diary-detail";
    }

    @GetMapping("/diary-petList")
    public String diaryPetList(Model model, Account account) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        account.setId(user.getId());

        model.addAttribute("petList", petService.getPetList(account));
        return "diary/diary-petList";
    }

}
