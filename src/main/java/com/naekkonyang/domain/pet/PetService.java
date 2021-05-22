package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    //펫 정보 신규 등록
    public Pet registerPet(Pet pet, Account account) {
        account.addPet(pet);
        pet.addAccount(account);
        petRepository.save(pet);
        return pet;
    }

    //펫 정보 id로 조회
    public Pet getPet(Pet pet) {
        Optional<Pet> byId =  petRepository.findById(pet.getId());
        return byId.orElse(pet);
    }

    //펫 정보 회원 id로 조회
    public List getPetList(Account account) {
        List<Pet> petList =  petRepository.findAllByAccount_Id(account.getId());
        return petList;
    }

    //펫 정보 수정
    public void updatePet(PetForm petForm, Account account) {

    }

}
