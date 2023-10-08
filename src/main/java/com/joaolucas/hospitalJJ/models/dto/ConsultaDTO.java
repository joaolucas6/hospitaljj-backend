package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ConsultaDTO {
    private Long id;
    private LocalDateTime dataDeCriacao;
    private Long medicoId;
    private Long pacienteId;
    private Long especialidadeId;
    private BigDecimal preco;
    private Status status;
    private String receitaMedica;
    private String observacoesMedica;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioTermino;

    public ConsultaDTO() {
    }

    public ConsultaDTO(Consulta consulta){
        setId(consulta.getId());
        setDataDeCriacao(consulta.getDataDeCriacao());
        if(consulta.getMedico() != null) setMedicoId(consulta.getMedico().getId());
        if(consulta.getPaciente() != null) setPacienteId(consulta.getPaciente().getId());
        if(consulta.getEspecialidade() != null) setEspecialidadeId(consulta.getEspecialidade().getId());
        setPreco(consulta.getPreco());
        setStatus(consulta.getStatus());
        setReceitaMedica(consulta.getReceitaMedica());
        setObservacoesMedica(consulta.getObservacoesMedica());
        setHorarioInicio(consulta.getHorarioInicio());
        setHorarioTermino(consulta.getHorarioTermino());
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

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
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
        ConsultaDTO that = (ConsultaDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(medicoId, that.medicoId) && Objects.equals(pacienteId, that.pacienteId) && Objects.equals(especialidadeId, that.especialidadeId) && Objects.equals(preco, that.preco) && status == that.status && Objects.equals(receitaMedica, that.receitaMedica) && Objects.equals(observacoesMedica, that.observacoesMedica) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioTermino, that.horarioTermino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataDeCriacao, medicoId, pacienteId, especialidadeId, preco, status, receitaMedica, observacoesMedica, horarioInicio, horarioTermino);
    }

    @Override
    public String toString() {
        return "ConsultaDTO{" +
                "id=" + id +
                ", dataDeCriacao=" + dataDeCriacao +
                ", medicoId=" + medicoId +
                ", pacienteId=" + pacienteId +
                ", especialidadeId=" + especialidadeId +
                ", preco=" + preco +
                ", status=" + status +
                ", receitaMedica='" + receitaMedica + '\'' +
                ", observacoesMedica='" + observacoesMedica + '\'' +
                ", horarioInicio=" + horarioInicio +
                ", horarioTermino=" + horarioTermino +
                '}';
    }
}
