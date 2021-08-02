package com.naekkonyang.domain.diary;

import com.naekkonyang.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByPet_idOrderByIdAsc(Long id);
}
