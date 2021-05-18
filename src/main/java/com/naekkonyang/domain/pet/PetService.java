package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

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
}
