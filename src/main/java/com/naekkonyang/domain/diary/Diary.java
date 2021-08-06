package com.naekkonyang.domain.diary;

import com.naekkonyang.domain.BaseTimeEntity;
import com.naekkonyang.domain.account.Account;
import com.naekkonyang.domain.pet.Pet;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 일기 순번
    @Column(nullable = false)
    private int num;

    // 일기 제목
    @Column(nullable = false)
    private String title;

    // 일기 내용
    @Column
    private String content;

    // 업로드 사진
    @Column
    private String picture;

    // 일기 댓글
    @Column
    private String reply;

    // 펫정보 관계매핑
    @ManyToOne
    private Pet pet;

    // 펫 정보 추가
    public void addPet(Pet pet) {
        Diary diary = new Diary();
        diary.setPet(pet);
    }

}
