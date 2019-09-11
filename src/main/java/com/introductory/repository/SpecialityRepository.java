package com.introductory.repository;

import com.introductory.entity.Speciality;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

    List<Speciality> findBySpecialityName(String specialityName);

}
