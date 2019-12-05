package com.example.backend.models.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.backend.models.entity.Especialidad;
import com.example.backend.models.entity.Usuario;

@Repository
public interface IEspecialidadDAO extends MongoRepository<Especialidad, String> {
    List<Especialidad> findAll();
    Especialidad findBy_id(String _id);
    
    @Query(value = "{ 'nombre' : ?0 }")
    Especialidad findByNombre(String nombre);


}