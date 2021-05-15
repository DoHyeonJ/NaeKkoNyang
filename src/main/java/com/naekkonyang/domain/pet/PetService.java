package com.naekkonyang.domain.pet;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void registerPet(Pet pet, Account account) {
        account.addPet(pet);
        pet.addAccount(account);
        Pet newPet = petRepository.save(pet);
    }

}
