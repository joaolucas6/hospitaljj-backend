package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.SolicitacaoConsulta;
import com.joaolucas.hospitalJJ.models.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class SolicitacaoConsultaDTO {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private String motivoDaConsulta;
    private Long especialidadeId;
    private LocalDateTime inicioDaConsulta;
    private LocalDateTime fimDaConsulta;
    private Status status;

    public SolicitacaoConsultaDTO() {
    }

    public SolicitacaoConsultaDTO(SolicitacaoConsulta solicitacaoConsulta){
        setId(solicitacaoConsulta.getId());
        if(solicitacaoConsulta.getPaciente() != null) setPacienteId(solicitacaoConsulta.getPaciente().getId());
        if(solicitacaoConsulta.getMedico() != null) setMedicoId(solicitacaoConsulta.getMedico().getId());
        setMotivoDaConsulta(solicitacaoConsulta.getMotivoDaConsulta());
        if(solicitacaoConsulta.getEspecialidade() != null) setEspecialidadeId(solicitacaoConsulta.getEspecialidade().getId());
        setInicioDaConsulta(solicitacaoConsulta.getInicioDaConsulta());
        setFimDaConsulta(solicitacaoConsulta.getFimDaConsulta());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getMotivoDaConsulta() {
        return motivoDaConsulta;
    }

    public void setMotivoDaConsulta(String motivoDaConsulta) {
        this.motivoDaConsulta = motivoDaConsulta;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
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
        SolicitacaoConsultaDTO userDTO = (SolicitacaoConsultaDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(pacienteId, userDTO.pacienteId) && Objects.equals(medicoId, userDTO.medicoId) && Objects.equals(motivoDaConsulta, userDTO.motivoDaConsulta) && Objects.equals(especialidadeId, userDTO.especialidadeId) && Objects.equals(inicioDaConsulta, userDTO.inicioDaConsulta) && Objects.equals(fimDaConsulta, userDTO.fimDaConsulta) && status == userDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pacienteId, medicoId, motivoDaConsulta, especialidadeId, inicioDaConsulta, fimDaConsulta, status);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", paciente=" + pacienteId +
                ", medico=" + medicoId +
                ", motivoDaConsulta='" + motivoDaConsulta + '\'' +
                ", especialidade=" + especialidadeId +
                ", inicioDaConsulta=" + inicioDaConsulta +
                ", fimDaConsulta=" + fimDaConsulta +
                ", status=" + status +
                '}';
    }
}
