package com.naekkonyang.domain.pet;

import lombok.Data;

@Data
public class PetForm {

    private Long id;

    private String pet_name;

    private String pet_birth;

    private String pet_gender;

    private String pet_neutralize;

    private String pet_type;

    private String pet_deleteYN = "N";
}
