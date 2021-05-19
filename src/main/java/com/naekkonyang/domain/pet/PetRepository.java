package com.naekkonyang.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByAccount_Id(Long id);

    Pet findByIdAndAccount_Id(Long petId, Long accountId);

}
