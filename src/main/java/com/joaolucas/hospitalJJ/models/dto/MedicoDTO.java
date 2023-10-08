package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.entities.Especialidade;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.entities.SolicitacaoConsulta;
import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicoDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private Genero genero;
    private String email;
    private String numeroTelefone;
    private Role role;
    private List<Long> consultasId = new ArrayList<>();
    private List<Long> especialidadesId = new ArrayList<>();
    private String numeroRegistro;
    private List<Long> solicitacoesConsultaId = new ArrayList<>();

    public MedicoDTO() {
    }

    public MedicoDTO(Medico medico){
        setId(medico.getId());
        setNome(medico.getNome());
        setSobrenome(medico.getSobrenome());
        setDataNascimento(medico.getDataNascimento());
        setCpf(medico.getCpf());
        setGenero(medico.getGenero());
        setEmail(medico.getEmail());
        setNumeroTelefone(medico.getNumeroTelefone());
        setRole(medico.getRole());
        setNumeroRegistro(medico.getNumeroRegistro());
        setConsultasId(medico.getConsultas().stream().map(Consulta::getId).toList());
        setEspecialidadesId(medico.getEspecialidades().stream().map(Especialidade::getId).toList());
        setSolicitacoesConsultaId(medico.getSolicitacoesConsulta().stream().map(SolicitacaoConsulta::getId).toList());
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

    public List<Long> getEspecialidadesId() {
        return especialidadesId;
    }

    public void setEspecialidadesId(List<Long> especialidadesId) {
        this.especialidadesId = especialidadesId;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
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
        MedicoDTO medicoDTO = (MedicoDTO) o;
        return Objects.equals(id, medicoDTO.id) && Objects.equals(nome, medicoDTO.nome) && Objects.equals(sobrenome, medicoDTO.sobrenome) && Objects.equals(dataNascimento, medicoDTO.dataNascimento) && Objects.equals(cpf, medicoDTO.cpf) && genero == medicoDTO.genero && Objects.equals(email, medicoDTO.email) && Objects.equals(numeroTelefone, medicoDTO.numeroTelefone) && role == medicoDTO.role && Objects.equals(consultasId, medicoDTO.consultasId) && Objects.equals(especialidadesId, medicoDTO.especialidadesId) && Objects.equals(numeroRegistro, medicoDTO.numeroRegistro) && Objects.equals(solicitacoesConsultaId, medicoDTO.solicitacoesConsultaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, dataNascimento, cpf, genero, email, numeroTelefone, role, consultasId, especialidadesId, numeroRegistro, solicitacoesConsultaId);
    }

    @Override
    public String toString() {
        return "MedicoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", genero=" + genero +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", role=" + role +
                ", consultasId=" + consultasId +
                ", especialidadesId=" + especialidadesId +
                ", numeroRegistro='" + numeroRegistro + '\'' +
                ", solicitacoesConsultaId=" + solicitacoesConsultaId +
                '}';
    }
}
