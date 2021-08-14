package com.naekkonyang.domain.diary;

import lombok.Data;

@Data
public class DiaryForm {

    private Long id;

    private Long num;

    private String title;

    private String content;

    private String picture;

    private String reply;

}
