package com.naekkonyang.domain.diary;

import com.naekkonyang.domain.pet.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

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

}
