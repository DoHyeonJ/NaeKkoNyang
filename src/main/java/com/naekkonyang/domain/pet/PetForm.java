package com.naekkonyang.domain.pet;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class PetForm {

    private String pet_name;

    private String pet_birth;

    private String pet_gender;

    private String pet_neutralize;

    private String pet_type;

}
