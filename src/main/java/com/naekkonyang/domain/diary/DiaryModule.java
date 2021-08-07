package com.naekkonyang.domain.diary;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DiaryModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}
