package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetForm;
import com.naekkonyang.domain.pet.PetService;
import com.naekkonyang.domain.user.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class PetController {

    private final PetService petService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    //펫 등록 페이지 Get
    @GetMapping("/pet-register")
    public String petRegister(Model model) {
        model.addAttribute(new PetForm());
        return "pet/pet-register";
    }

    //펫 등록 페이지 Post
    @PostMapping("/pet-register")
    public String petRegisterSubmit(Account account, @Valid PetForm petForm) {
        //현재는 Session에서 유저 정보를 가져오는 구조 추후 변경예정
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        account.setId(user.getId());
        petService.registerPet(modelMapper.map(petForm, Pet.class), account);

        return "pet/pet-register";
    }

}
