package com.example.backend.models.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.backend.models.entity.Medico;

public interface IMedicoDAO extends MongoRepository<Medico, String>{

	@Query(value = "{ 'dni' : ?0 }")
	Medico findByDni(String dni);
}
