package com.joaolucas.hospitalJJ.models.dto;

import com.joaolucas.hospitalJJ.models.entities.Admin;
import com.joaolucas.hospitalJJ.models.enums.Genero;
import com.joaolucas.hospitalJJ.models.enums.Role;

import java.time.LocalDate;
import java.util.Objects;

public class AdminDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private Genero genero;
    private String email;
    private String numeroTelefone;
    private Role role;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        setId(admin.getId());
        setNome(admin.getNome());
        setSobrenome(admin.getSobrenome());
        setDataNascimento(admin.getDataNascimento());
        setCpf(admin.getCpf());
        setGenero(admin.getGenero());
        setEmail(admin.getEmail());
        setNumeroTelefone(admin.getNumeroTelefone());
        setRole(admin.getRole());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(id, adminDTO.id) && Objects.equals(nome, adminDTO.nome) && Objects.equals(sobrenome, adminDTO.sobrenome) && Objects.equals(dataNascimento, adminDTO.dataNascimento) && Objects.equals(cpf, adminDTO.cpf) && genero == adminDTO.genero && Objects.equals(email, adminDTO.email) && Objects.equals(numeroTelefone, adminDTO.numeroTelefone) && role == adminDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, dataNascimento, cpf, genero, email, numeroTelefone, role);
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", genero=" + genero +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", role=" + role +
                '}';
    }
}
