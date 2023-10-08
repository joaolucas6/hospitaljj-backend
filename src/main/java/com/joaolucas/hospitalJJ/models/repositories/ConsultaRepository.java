package com.joaolucas.hospitalJJ.models.repositories;

import com.joaolucas.hospitalJJ.models.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
