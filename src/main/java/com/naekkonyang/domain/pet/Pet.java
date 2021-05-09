package com.naekkonyang.domain.pet;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pet_id;

    @Column(nullable = false)
    private String pet_name;

    @Column
    private LocalDateTime pet_birth;

    @Column
    private String pet_gender;

    @Column
    private String pet_neutralize;

    @Column
    private String pet_type;


}
