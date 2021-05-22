package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.account.Account;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원정보 관계매핑
    @ManyToOne
    private Account account;

    // 펫 이름
    @Column(nullable = false)
    private String pet_name;

    // 생일
    @Column
    private String pet_birth;

    // 성별
    @Column
    private String pet_gender;

    // 중성화 여부
    @Column
    private String pet_neutralize;

    // 종류
    @Column
    private String pet_type;

    // 사진
    @Column
    private String pet_picture;

    // 펫 정보 삭제 유무
    @Column
    private String deleteYN;

    // 펫 정보에 회원정보 추가
    public void addAccount(Account account) {
        Pet pet = new Pet();
        pet.setAccount(account);
    }

}
