package com.joaolucas.hospitalJJ.models.entities;

import com.joaolucas.hospitalJJ.models.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_solicitacoes_consulta")
public class SolicitacaoConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @Column(name = "motivo_da_consulta", length = 500)
    private String motivoDaConsulta;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    @Column(name = "inicio_da_consulta")
    private LocalDateTime inicioDaConsulta;

    @Column(name = "fim_da_consulta")
    private LocalDateTime fimDaConsulta;

    @Enumerated(EnumType.STRING)
    private Status status;

    public SolicitacaoConsulta() {
    }

    public SolicitacaoConsulta(Long id, Paciente paciente, Medico medico, String motivoDaConsulta, Especialidade especialidade, LocalDateTime inicioDaConsulta, LocalDateTime fimDaConsulta, Status status) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.motivoDaConsulta = motivoDaConsulta;
        this.especialidade = especialidade;
        this.inicioDaConsulta = inicioDaConsulta;
        this.fimDaConsulta = fimDaConsulta;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getMotivoDaConsulta() {
        return motivoDaConsulta;
    }

    public void setMotivoDaConsulta(String motivoDaConsulta) {
        this.motivoDaConsulta = motivoDaConsulta;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getInicioDaConsulta() {
        return inicioDaConsulta;
    }

    public void setInicioDaConsulta(LocalDateTime inicioDaConsulta) {
        this.inicioDaConsulta = inicioDaConsulta;
    }

    public LocalDateTime getFimDaConsulta() {
        return fimDaConsulta;
    }

    public void setFimDaConsulta(LocalDateTime fimDaConsulta) {
        this.fimDaConsulta = fimDaConsulta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitacaoConsulta that = (SolicitacaoConsulta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
