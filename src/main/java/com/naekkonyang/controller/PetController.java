package com.naekkonyang.controller;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetForm;
import com.naekkonyang.domain.pet.PetRepository;
import com.naekkonyang.domain.pet.PetService;
import com.naekkonyang.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class PetController {

    private final PetService petService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;
    private final PetRepository petRepository;

    //펫 등록 페이지 Get
    @GetMapping("/pet-register")
    public String petRegister(Model model) {
        model.addAttribute(new PetForm());
        return "pet/pet-register";
    }

    //펫 등록 페이지 Post
    @PostMapping("/pet-register")
    public String petRegisterSubmit(Account account, @Valid PetForm petForm, RedirectAttributes redirectAttributes) {

        //펫 타입의 변수를 선언하고 저장된 펫 객체 리다이렉트 시켜줌
        Pet newPet = new Pet();

        //현재는 Session에서 유저 정보를 가져오는 구조 추후 변경예정
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        //추후 수정할 부분 회원정보에서 id값 받아오는것
        account.setId(user.getId());

        newPet = petService.registerPet(modelMapper.map(petForm, Pet.class), account);
        redirectAttributes.addAttribute("petInfo", newPet);
        return "redirect:/pet-register-completed";
    }

    //펫 등록 완료페이지
    @GetMapping("/pet-register-completed")
    public String petRegisterCompletedView(Model model, @RequestParam(value ="petInfo", required = false) Pet pet) {
        //리다이렉트 받아온 펫 객체 Service 거쳐서 정보 가져오기
        Pet petInfo = petService.getPet(pet);

        model.addAttribute("pet", petInfo);

        return "pet/pet-register-completed-view";
    }

    //펫 목록 페이지
    @GetMapping("/pet-list")
    public String petListView(Model model, Account account) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        account.setId(user.getId());

        //회원 id에 매핑되는 펫 정보 List 뿌려줌
        model.addAttribute("petList", petRepository.findAllByAccount_Id(account.getId()));

        return "pet/pet-list";
    }

    //펫 상세 페이지
    @GetMapping("/pet-detail/{id}")
    public String petDetail(Account account, @PathVariable("id") Pet pet,Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        account.setId(user.getId());

        //펫 id와 회원 id 매핑하여 펫 정보 가져옴
        model.addAttribute("petDetail", petRepository.findByIdAndAccount_Id(account.getId(), pet.getId()));
        return "pet/pet-detail";
    }
}














