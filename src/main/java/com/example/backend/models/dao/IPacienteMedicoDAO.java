package com.example.backend.models.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.backend.models.entity.PacienteMedico;

public interface IPacienteMedicoDAO extends MongoRepository<PacienteMedico, String> {


	@Query(value = "{ 'dniPaciente' : ?0, 'dniMedico' : ?1}")
	PacienteMedico findByCustom(String dniPaciente, String dniMedico);
	
}
