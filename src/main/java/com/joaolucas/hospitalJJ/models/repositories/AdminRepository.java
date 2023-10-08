package com.joaolucas.hospitalJJ.models.repositories;

import com.joaolucas.hospitalJJ.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
