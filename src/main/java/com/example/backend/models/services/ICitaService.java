package com.example.backend.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.backend.models.entity.Cita;
import com.example.backend.models.entity.Medico;
import com.example.backend.models.entity.Paciente;

public interface ICitaService {

	public ArrayList<Cita> findByPacienteAndFecha(Paciente paciente, Date fecha);
	public ArrayList<Cita> findByMedico(Medico medico);
	public ArrayList<Cita> findByPaciente(Paciente paciente);
	public void saveCita(Cita cita);

}
