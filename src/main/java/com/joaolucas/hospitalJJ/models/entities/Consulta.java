package com.joaolucas.hospitalJJ.models.entities;

import com.joaolucas.hospitalJJ.models.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_consultas")
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    @Column(name = "preco")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "receita_medica")
    private String receitaMedica;

    @Column(name = "observacoes_medica")
    private String observacoesMedica;

    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;

    @Column(name = "horario_termino")
    private LocalDateTime horarioTermino;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataDeCriacao, Medico medico, Paciente paciente, Especialidade especialidade, BigDecimal preco, Status status, String receitaMedica, String observacoesMedica, LocalDateTime horarioInicio, LocalDateTime horarioTermino) {
        this.id = id;
        this.dataDeCriacao = dataDeCriacao;
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.preco = preco;
        this.status = status;
        this.receitaMedica = receitaMedica;
        this.observacoesMedica = observacoesMedica;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReceitaMedica() {
        return receitaMedica;
    }

    public void setReceitaMedica(String receitaMedica) {
        this.receitaMedica = receitaMedica;
    }

    public String getObservacoesMedica() {
        return observacoesMedica;
    }

    public void setObservacoesMedica(String observacoesMedica) {
        this.observacoesMedica = observacoesMedica;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(LocalDateTime horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



