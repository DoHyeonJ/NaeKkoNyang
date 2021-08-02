package com.naekkonyang.controller;

import com.naekkonyang.domain.diary.DiaryService;
import com.naekkonyang.domain.pet.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/diary-list/{id}")
    public String diaryList(Model model, @PathVariable("id") Pet pet) {
        model.addAttribute("diaryList", diaryService.getDiaryList(pet));
        return "diary/diary-list";
    }

    @GetMapping("/diary-registration")
    public String diaryRegistration() {
        return "diary/diary-registration";
    }

    @GetMapping("/diary-detail")
    public String diaryDetail() {
        return "diary/diary-detail";
    }

}
