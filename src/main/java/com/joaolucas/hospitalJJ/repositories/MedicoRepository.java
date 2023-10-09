package com.joaolucas.hospitalJJ.repositories;

import com.joaolucas.hospitalJJ.models.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
