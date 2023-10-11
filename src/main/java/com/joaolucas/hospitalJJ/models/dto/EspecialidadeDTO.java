package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.entities.Especialidade;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.entities.SolicitacaoConsulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EspecialidadeDTO {
    private Long id;
    private String nome;
    private String descricao;
    private List<Long> medicosId = new ArrayList<>();
    private List<Long> consultasId = new ArrayList<>();

    private List<Long> solicitacoesConsultaId = new ArrayList<>();

    public EspecialidadeDTO() {
    }

    public EspecialidadeDTO(Especialidade especialidade){
        setId(especialidade.getId());
        setNome(especialidade.getNome());
        setDescricao(especialidade.getDescricao());
        setMedicosId(especialidade.getMedicos().stream().map(Medico::getId).toList());
        setConsultasId(especialidade.getConsultas().stream().map(Consulta::getId).toList());
        setSolicitacoesConsultaId(especialidade.getSolicitacoesConsulta().stream().map(SolicitacaoConsulta::getId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Long> getMedicosId() {
        return medicosId;
    }

    public void setMedicosId(List<Long> medicosId) {
        this.medicosId = medicosId;
    }

    public List<Long> getConsultasId() {
        return consultasId;
    }

    public void setConsultasId(List<Long> consultasId) {
        this.consultasId = consultasId;
    }

    public List<Long> getSolicitacoesConsultaId() {
        return solicitacoesConsultaId;
    }

    public void setSolicitacoesConsultaId(List<Long> solicitacoesConsultaId) {
        this.solicitacoesConsultaId = solicitacoesConsultaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadeDTO that = (EspecialidadeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(medicosId, that.medicosId) && Objects.equals(consultasId, that.consultasId) && Objects.equals(solicitacoesConsultaId, that.solicitacoesConsultaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, medicosId, consultasId, solicitacoesConsultaId);
    }

    @Override
    public String toString() {
        return "EspecialidadeDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", medicosId=" + medicosId +
                ", consultasId=" + consultasId +
                ", solicitacoesConsultaId=" + solicitacoesConsultaId +
                '}';
    }
}
