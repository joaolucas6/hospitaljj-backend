package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.AdminDTO;
import com.joaolucas.hospitalJJ.models.entities.Admin;
import com.joaolucas.hospitalJJ.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public List<AdminDTO> encontrarTodos(){
        return adminRepository.findAll().stream().map(AdminDTO::new).toList();
    }

    public AdminDTO encontrarPorId(Long id){
        return new AdminDTO(adminRepository.findById(id).orElseThrow());
    }

    public AdminDTO atualizar(Long id, AdminDTO adminDTO){
        Admin admin = adminRepository.findById(id).orElseThrow();
        if(adminDTO.getNome() != null) admin.setNome(adminDTO.getNome());
        if(adminDTO.getSobrenome() != null) admin.setSobrenome(adminDTO.getSobrenome());
        if(adminDTO.getDataNascimento() != null) admin.setDataNascimento(adminDTO.getDataNascimento());
        if(adminDTO.getGenero() != null) admin.setGenero(adminDTO.getGenero());
        if(adminDTO.getNumeroTelefone() != null) admin.setNumeroTelefone(adminDTO.getNumeroTelefone());
        if(adminDTO.getEmail() != null) admin.setEmail(adminDTO.getEmail());

        return new AdminDTO(adminRepository.save(admin));
    }

    public void deletar(Long id){
        Admin admin = adminRepository.findById(id).orElseThrow();
        adminRepository.delete(admin);
    }
}
