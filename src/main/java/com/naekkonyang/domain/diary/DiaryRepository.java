package com.naekkonyang.domain.diary;

import com.naekkonyang.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByPet_idOrderByIdAsc(Long id);
    Diary findAllById(Long id);
    Optional<Diary> findTopByPet_idOrderByCreatedDateDesc(Long id);
}
