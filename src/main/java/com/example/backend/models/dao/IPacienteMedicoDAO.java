package com.example.backend.models.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.models.entity.PacienteMedico;

public interface IPacienteMedicoDAO extends MongoRepository<PacienteMedico, String> {

}
