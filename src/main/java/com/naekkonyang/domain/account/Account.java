package com.naekkonyang.domain.account;


import com.naekkonyang.domain.pet.Pet;
import lombok.*;
import javax.persistence.*;
import java.time.DateTimeException;
import java.util.HashSet;
import java.util.Set;
import com.naekkonyang.domain.BaseTimeEntity;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //펫 정보 관계매핑
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Pet> pet = new HashSet<>();

    // 이름
    @Column(nullable = false)
    private String name;

    // 이메일
    @Column(nullable = false)
    private String email;

    // 소셜로그인 시 땡겨오는 프로필 사진
    @Column
    private String picture;

    //사용자 enum 설정 현재는 게스트사용자 기본값
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 회원정보 갱신
    public Account update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    // 회원정보에 펫정보 추가
    public void addPet(Pet pet) {
        this.pet.add(pet);
        pet.setAccount(this);
    }

}