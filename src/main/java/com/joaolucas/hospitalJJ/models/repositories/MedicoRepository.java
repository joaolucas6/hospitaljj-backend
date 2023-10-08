package com.joaolucas.hospitalJJ.models.repositories;

import com.joaolucas.hospitalJJ.models.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
