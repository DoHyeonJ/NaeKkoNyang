package com.naekkonyang.domain.diary;

import com.naekkonyang.config.SessionUser;
import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.pet.Pet;
import com.naekkonyang.domain.pet.PetForm;
import com.naekkonyang.domain.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PetRepository petRepository;
    private final HttpSession httpSession;
    private final DiaryModuleRepository diaryModuleRepository;
    private final ModelMapper modelMapper;
    private static final String FILE_PATH = "src/main/resources/static/diaryList.xlsx";

    //일기 정보 펫 id로 조회
    public List<Diary> getDiaryList(Pet pet) {
        List<Diary> diaryList = diaryRepository.findAllByPet_idOrderByIdAsc(pet.getId());
        return diaryList;
    }

    // 일기 권한 여부 체크
    public boolean checkAccount(Diary diary) {
        Diary pet = diaryRepository.findAllById(diary.getId()); // 다이어리 정보 추출
        Pet account = petRepository.findAllByAccount_id(pet.getPet().getId()); // 추출한 다이어리 정보 통해서 펫정보 추출
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 추출한 id 값과 세션값 비교 ( 불일치시 false )
        if (user.getId().equals(account.getId())) {
            return false;
        }

        return true;
    }

    // 다이어리 생성 여부 체크
    public String checkNewDiary(Pet pet) {
        List<Diary> diary = diaryRepository.findAllByPet_idOrderByIdAsc(pet.getId());
        DiaryModule diaryModule = new DiaryModule();

        try {
            // 일기가 아예 비어있으면 새로운 일기를 던져준다. TODO : "설정한 시간에 맞춰서 일기가 업로드 되게 변경"
            if (diary.isEmpty()) {
                diaryModule = diaryModuleRepository.findAllById((long) 1);
            } else {
                int lastIndex = diary.size() - 1;
                LocalDateTime createdDate = diary.get(lastIndex).getCreatedDate(); // 일기 생성일
                LocalDateTime modifiedDate = diary.get(lastIndex).getModifiedDate(); // 일기 수정일
                LocalDateTime dateNow = LocalDateTime.now(); // 현재시간
                long between = ChronoUnit.SECONDS.between(createdDate, dateNow); // 생성일과 현재시간 비교값

                // 일기를 수정했으며, 일기 생성한지 24시간이 지났으면 새로운 일기를 던져준다.
                if (!createdDate.isEqual(modifiedDate) && between > 86400) {
                    diaryModule = diaryModuleRepository.findAllById(diary.get(lastIndex).getNum() + 1);
                } else {
                    return "";
                }
            }
            insertDiary(pet, diaryModule);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }

    // 다이어리 정보 insert
    public String insertDiary(Pet pet, DiaryModule diaryModule) {
        try {
            Diary diary = new Diary();
            diary.addPet(pet);
            diary.setNum(diaryModule.getId());
            diary.setTitle(diaryModule.getTitle());
            diary.setContent(diaryModule.getContent());
            pet.addDiary(diary);
            diaryRepository.save(diary);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }

    // 다이어리 정보 update
    public void updateDiary(Diary diary, DiaryForm diaryForm) {
        try {
            modelMapper.map(diaryForm, diary);
            diaryRepository.save(diary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
