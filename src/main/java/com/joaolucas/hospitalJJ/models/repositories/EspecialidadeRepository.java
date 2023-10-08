package com.joaolucas.hospitalJJ.models.repositories;

import com.joaolucas.hospitalJJ.models.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
