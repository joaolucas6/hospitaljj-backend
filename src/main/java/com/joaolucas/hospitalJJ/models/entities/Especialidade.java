package com.joaolucas.hospitalJJ.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_especialidades")
public class Especialidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "tb_especialidades_medicos",
            joinColumns = @JoinColumn(name = "especialidade_id"),
            inverseJoinColumns = @JoinColumn(name = "medico_id")
    )
    private List<Medico> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "especialidade")
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "especialidade")
    private List<SolicitacaoConsulta> solicitacoesConsulta = new ArrayList<>();

    public Especialidade(){}

    public Especialidade(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
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
        Especialidade that = (Especialidade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
