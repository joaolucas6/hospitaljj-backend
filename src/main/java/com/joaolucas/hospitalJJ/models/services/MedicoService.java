package com.joaolucas.hospitalJJ.models.services;

import com.joaolucas.hospitalJJ.models.dto.MedicoDTO;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.repositories.MedicoRepository;
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
        return new MedicoDTO(medicoRepository.findById(id).orElseThrow());
    }

    public MedicoDTO atualizar(Long id, MedicoDTO medicoDTO){
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
        Medico medico = medicoRepository.findById(id).orElseThrow();
        medicoRepository.delete(medico);
    }
}
