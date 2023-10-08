package com.joaolucas.hospitalJJ.models.entities;

import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("paciente")
public class Paciente extends User{
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();
    @OneToMany(mappedBy = "paciente")
    private List<SolicitacaoConsulta> solicitacoesDeConsulta = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(Long id, String nome, String sobrenome, LocalDate dataNascimento, String cpf, Genero genero, String email, String senha, String numeroTelefone, Role role, List<Consulta> consultas, List<SolicitacaoConsulta> solicitacoesDeConsulta) {
        super(id, nome, sobrenome, dataNascimento, cpf, genero, email, senha, numeroTelefone, role);
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<SolicitacaoConsulta> getSolicitacoesDeConsulta() {
        return solicitacoesDeConsulta;
    }

    public void setSolicitacoesDeConsulta(List<SolicitacaoConsulta> solicitacoesDeConsulta) {
        this.solicitacoesDeConsulta = solicitacoesDeConsulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(consultas, paciente.consultas) && Objects.equals(solicitacoesDeConsulta, paciente.solicitacoesDeConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultas, solicitacoesDeConsulta);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "consultas=" + consultas +
                ", solicitacoesDeConsulta=" + solicitacoesDeConsulta +
                '}';
    }
}
