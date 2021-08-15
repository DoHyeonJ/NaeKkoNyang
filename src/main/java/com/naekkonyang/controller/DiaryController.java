package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.diary.Diary;
import com.naekkonyang.domain.diary.DiaryForm;
import com.naekkonyang.domain.diary.DiaryService;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetForm;
import com.naekkonyang.domain.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final HttpSession httpSession;
    private final PetService petService;
    private final ModelMapper modelMapper;

    @GetMapping("/diary-list/{id}")
    public String diaryList(Model model, @PathVariable("id") Pet pet, Account account) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        account.setId(user.getId());

        // 펫 권한여부 조회 권한없을시 리스트로 리다이렉트
        if(petService.checkAccount(account, pet)) { return "redirect:/pet-list";}

        diaryService.checkNewDiary(pet);

        List<Diary> diaryList = diaryService.getDiaryList(pet);
        Collections.reverse(diaryList);

        model.addAttribute("diaryList", diaryList);
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

        model.addAttribute(modelMapper.map(diary, DiaryForm.class));
        return "diary/diary-detail";
    }

    @PostMapping("/diary-update/{id}")
    public String diaryDetailUpdate(@PathVariable("id") Diary diary,DiaryForm diaryForm) {

        diaryService.updateDiary(diary, diaryForm);
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
