package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.PacienteDTO;
import com.joaolucas.hospitalJJ.models.entities.Paciente;
import com.joaolucas.hospitalJJ.repositories.PacienteRepository;
import com.joaolucas.hospitalJJ.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<PacienteDTO> encontrarTodos(){
        return pacienteRepository.findAll().stream().map(PacienteDTO::new).toList();
    }

    public PacienteDTO encontrarPorId(Long id){
        return new PacienteDTO(pacienteRepository.findById(id).orElseThrow());
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO){
        if(!ValidacaoDeDados.validarDadosDoUser(pacienteDTO)) throw new RuntimeException();

        Paciente paciente = pacienteRepository.findById(id).orElseThrow();
        if(pacienteDTO.getNome() != null) paciente.setNome(pacienteDTO.getNome());
        if(pacienteDTO.getSobrenome() != null) paciente.setSobrenome(pacienteDTO.getSobrenome());
        if(pacienteDTO.getDataNascimento() != null) paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        if(pacienteDTO.getGenero() != null) paciente.setGenero(pacienteDTO.getGenero());
        if(pacienteDTO.getNumeroTelefone() != null) paciente.setNumeroTelefone(pacienteDTO.getNumeroTelefone());
        if(pacienteDTO.getEmail() != null) paciente.setEmail(pacienteDTO.getEmail());

        return new PacienteDTO(pacienteRepository.save(paciente));
    }

    public void deletar(Long id){
        Paciente paciente = pacienteRepository.findById(id).orElseThrow();
        pacienteRepository.delete(paciente);
    }
}
