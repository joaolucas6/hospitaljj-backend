package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.exceptions.BadRequestException;
import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
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
        return new PacienteDTO(pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não foi encontrado com ID: " + id)));
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO){
        if(!ValidacaoDeDados.validarDadosDoUser(pacienteDTO)) throw new BadRequestException("Os dados do paciente são inválidos");

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
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não foi encontrado com ID: " + id));
        pacienteRepository.delete(paciente);
    }
}
