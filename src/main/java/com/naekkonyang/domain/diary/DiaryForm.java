package com.naekkonyang.domain.diary;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class DiaryForm {

    private Long id;

    private Long num;

    private String title;

    private String content;

    private String picture;

    private String reply;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
