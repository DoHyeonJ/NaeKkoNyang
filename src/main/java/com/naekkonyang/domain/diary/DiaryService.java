package com.naekkonyang.domain.diary;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PetRepository petRepository;
    private final HttpSession httpSession;

    //일기 정보 펫 id로 조회
    public List<Diary> getDiaryList(Pet pet) {
        List<Diary> diaryList = diaryRepository.findAllByPet_idOrderByIdAsc(pet.getId());
        return diaryList;
    }

    // 펫 생성시 첫번째 다이어리 정보 insert
    public String insertFirstDiary(Pet pet) {
        Diary diary = new Diary();
        diary.addPet(pet);
        diary.setNum(1);
        diary.setTitle("우리 아이를 소개합니다");
        diary.setContent("우리 아이의 예쁜 모습을 사진으로 보여주세요 :)");
        pet.addDiary(diary);
        diaryRepository.save(diary);
        return "";
    }

    // 일기 권한 여부 체크
    public boolean checkAccount(Diary diary) {
        Diary pet = diaryRepository.findByPet_id(diary.getId());
        Pet account = petRepository.findByAccount_id(pet.getId());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user.getId().equals(account.getId())) {
            return false;
        }

        return true;
    }
}
