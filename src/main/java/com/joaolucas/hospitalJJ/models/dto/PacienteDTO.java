package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.entities.Paciente;
import com.joaolucas.hospitalJJ.models.entities.SolicitacaoConsulta;
import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacienteDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private Genero genero;
    private String email;
    private String senha;
    private String numeroTelefone;
    private Role role;
    private List<Long> consultasId = new ArrayList<>();
    private List<Long> solicitacoesDeConsultaId = new ArrayList<>();


    public PacienteDTO() {
    }

    public PacienteDTO(Paciente paciente) {
        setId(paciente.getId());
        setNome(paciente.getNome());
        setSobrenome(paciente.getSobrenome());
        setDataNascimento(paciente.getDataNascimento());
        setCpf(paciente.getCpf());
        setGenero(paciente.getGenero());
        setEmail(paciente.getEmail());
        setSenha(paciente.getSenha());
        setNumeroTelefone(paciente.getNumeroTelefone());
        setRole(paciente.getRole());
        setConsultasId(paciente.getConsultas().stream().map(Consulta::getId).toList());
        setSolicitacoesDeConsultaId(paciente.getSolicitacoesDeConsulta().stream().map(SolicitacaoConsulta::getId).toList());
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getConsultasId() {
        return consultasId;
    }

    public void setConsultasId(List<Long> consultasId) {
        this.consultasId = consultasId;
    }

    public List<Long> getSolicitacoesDeConsultaId() {
        return solicitacoesDeConsultaId;
    }

    public void setSolicitacoesDeConsultaId(List<Long> solicitacoesDeConsultaId) {
        this.solicitacoesDeConsultaId = solicitacoesDeConsultaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacienteDTO that = (PacienteDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sobrenome, that.sobrenome) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(cpf, that.cpf) && genero == that.genero && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && Objects.equals(numeroTelefone, that.numeroTelefone) && role == that.role && Objects.equals(consultasId, that.consultasId) && Objects.equals(solicitacoesDeConsultaId, that.solicitacoesDeConsultaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, dataNascimento, cpf, genero, email, senha, numeroTelefone, role, consultasId, solicitacoesDeConsultaId);
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", genero=" + genero +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", role=" + role +
                ", consultasId=" + consultasId +
                ", solicitacoesDeConsultaId=" + solicitacoesDeConsultaId +
                '}';
    }
}
