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
    public List getDiaryList(Pet pet) {
        List<Diary> diaryList = diaryRepository.findAllByPet_idOrderByIdAsc(pet.getId());
        return diaryList;
    }

}
