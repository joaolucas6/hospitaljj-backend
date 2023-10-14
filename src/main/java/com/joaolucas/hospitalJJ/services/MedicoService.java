package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.exceptions.BadRequestException;
import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
import com.joaolucas.hospitalJJ.models.dto.MedicoDTO;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.repositories.MedicoRepository;
import com.joaolucas.hospitalJJ.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public List<MedicoDTO> encontrarTodos(){
        return medicoRepository.findAll().stream().map(MedicoDTO::new).toList();
    }

    public MedicoDTO encontrarPorId(Long id){
        return new MedicoDTO(medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não foi encontrado com ID: " + id)));
    }

    public MedicoDTO atualizar(Long id, MedicoDTO medicoDTO){
        if(!ValidacaoDeDados.validarDadosDoUser(medicoDTO)) throw new BadRequestException("Dados do médico são inválidos");

        Medico medico = medicoRepository.findById(id).orElseThrow();
        if(medicoDTO.getNome() != null) medico.setNome(medicoDTO.getNome());
        if(medicoDTO.getSobrenome() != null) medico.setSobrenome(medicoDTO.getSobrenome());
        if(medicoDTO.getDataNascimento() != null) medico.setDataNascimento(medicoDTO.getDataNascimento());
        if(medicoDTO.getGenero() != null) medico.setGenero(medicoDTO.getGenero());
        if(medicoDTO.getNumeroTelefone() != null) medico.setNumeroTelefone(medicoDTO.getNumeroTelefone());
        if(medicoDTO.getEmail() != null) medico.setEmail(medicoDTO.getEmail());

        return new MedicoDTO(medicoRepository.save(medico));
    }

    public void deletar(Long id){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não foi encontrado com ID: " + id));
        medicoRepository.delete(medico);
    }
}
