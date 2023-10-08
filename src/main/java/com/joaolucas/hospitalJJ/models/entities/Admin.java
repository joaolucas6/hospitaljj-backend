package com.joaolucas.hospitalJJ.models.entities;

import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User implements Serializable{
    public Admin() {
        setRole(Role.ADMIN);
    }

    public Admin(Long id, String nome, String sobrenome, LocalDate dataNascimento, String cpf, Genero genero, String email, String senha, String numeroTelefone, Role role) {
        super(id, nome, sobrenome, dataNascimento, cpf, genero, email, senha, numeroTelefone, role);
        setRole(Role.ADMIN);
    }

}
