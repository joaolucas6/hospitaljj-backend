package com.joaolucas.hospitalJJ.repositories;

import com.joaolucas.hospitalJJ.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
