package com.naekkonyang.domain.pet;

import com.naekkonyang.domain.user.Account;
import com.naekkonyang.domain.user.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pet_id;

    @ManyToOne
    private Account account;

    @Column(nullable = false)
    private String pet_name;

    @Column
    private String pet_birth;

    @Column
    private String pet_gender;

    @Column
    private String pet_neutralize;

    @Column
    private String pet_type;

    public void addAccount(Account account) {
        Pet pet = new Pet();
        pet.setAccount(account);
    }

}
