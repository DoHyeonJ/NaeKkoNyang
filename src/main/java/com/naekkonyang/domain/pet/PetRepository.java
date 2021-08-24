package com.naekkonyang.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByAccount_idOrderByIdAsc(Long id);
    List<Pet> findByAccount_idAndId(Long account_id, Long pet_id);
    Pet findAllById(Long id);
}
