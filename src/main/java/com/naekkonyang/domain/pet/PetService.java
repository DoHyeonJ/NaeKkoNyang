package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    public void registerPet(Pet pet, Account account) {
        account.addPet(pet);
        pet.addAccount(account);
        petRepository.save(pet);
    }
}
