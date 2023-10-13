package com.joaolucas.hospitalJJ.models.entities;

import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("medico")
public class Medico extends User implements Serializable {

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();

    @ManyToMany(mappedBy = "medicos")
    private List<Especialidade> especialidades = new ArrayList<>();

    @Column(name = "numero_registro")
    private String numeroRegistro;

    @OneToMany(mappedBy = "medico")
    private List<SolicitacaoConsulta> solicitacoesConsulta = new ArrayList<>();

    public Medico() {
        setRole(Role.MEDICO);
    }

    public Medico(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Medico(Long id, String nome, String sobrenome, LocalDate dataNascimento, String cpf, Genero genero, String email, String senha, String numeroTelefone, Role role, String numeroRegistro) {
        super(id, nome, sobrenome, dataNascimento, cpf, genero, email, senha, numeroTelefone, role);
        setRole(Role.MEDICO);
        this.numeroRegistro = numeroRegistro;
    }

    public Medico(Long id, String nome, String sobrenome, LocalDate dataNascimento, String cpf, Genero genero, String email, String senha, String numeroTelefone, Role role) {
        super(id, nome, sobrenome, dataNascimento, cpf, genero, email, senha, numeroTelefone, role);
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public List<SolicitacaoConsulta> getSolicitacoesConsulta() {
        return solicitacoesConsulta;
    }

    public void setSolicitacoesConsulta(List<SolicitacaoConsulta> solicitacoesConsulta) {
        this.solicitacoesConsulta = solicitacoesConsulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(consultas, medico.consultas) && Objects.equals(especialidades, medico.especialidades) && Objects.equals(numeroRegistro, medico.numeroRegistro) && Objects.equals(solicitacoesConsulta, medico.solicitacoesConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultas, especialidades, numeroRegistro, solicitacoesConsulta);
    }
}
