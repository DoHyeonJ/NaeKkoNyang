package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetForm;
import com.naekkonyang.domain.pet.PetRepository;
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

    private final PetRepository petRepository;
    private final PetService petService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/pet-register")
    public String petRegister(Model model) {
        model.addAttribute(new PetForm());
        return "pet/pet-register";
    }

    @PostMapping("/pet-register")
    public String petRegisterSubmit(Account account, @Valid PetForm petForm) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        account.setId(user.getId());
        System.out.println(account.getId());
        petService.registerPet(modelMapper.map(petForm, Pet.class), account);

        return "pet/pet-register";
    }

}
