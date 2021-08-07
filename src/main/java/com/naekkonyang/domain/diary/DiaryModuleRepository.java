package com.naekkonyang.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryModuleRepository extends JpaRepository<DiaryModule, Long> {
    DiaryModule findAllById(Long id);
}
