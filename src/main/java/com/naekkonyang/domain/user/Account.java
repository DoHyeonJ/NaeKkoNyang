package com.naekkonyang.domain.user;


import com.naekkonyang.domain.pet.Pet;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Pet> pet = new HashSet<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //사용자 enum 설정 현재는 게스트사용자 기본값
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Account update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void addPet(Pet pet) {
        this.pet.add(pet);
        pet.setAccount(this);
    }

}