package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.diary.DiaryService;
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
    private final DiaryService diaryService;

    //펫 정보 신규 등록
    public Pet registerPet(Pet pet, Account account) {
        try {
            account.addPet(pet);
            pet.addAccount(account);
            petRepository.save(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pet;
    }

    //펫 정보 id로 조회
    public Pet getPet(Pet pet) {
        Optional<Pet> byId =  petRepository.findById(pet.getId());
        return byId.orElse(pet);
    }

    //펫 정보 회원 id로 조회
    public List<Pet> getPetList(Account account) {
        List<Pet> petList =  petRepository.findAllByAccount_idOrderByIdAsc(account.getId());

        //삭제된 펫은 제외
        for (int i=0; i<petList.size();) {
            if (petList.get(i).getDeleteYN().equals("Y")) {
                petList.remove(i);
            } else {
                i++;
            }
        }

        return petList;
    }

    //펫 정보 수정
    public void updatePet(Pet pet, PetForm petForm) {
        try {
            modelMapper.map(petForm, pet);
            petRepository.save(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //펫 권한 확인
    public boolean checkAccount(Account account, Pet pet) {
        List<Pet> petList = petRepository.findByAccount_idAndId(account.getId(), pet.getId());

        if (petList.isEmpty()) {
            return true;
        }
        return false;
    }

}
