package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.exceptions.BadRequestException;
import com.joaolucas.hospitalJJ.exceptions.BusinessLogicException;
import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
import com.joaolucas.hospitalJJ.models.dto.EspecialidadeDTO;
import com.joaolucas.hospitalJJ.models.entities.Especialidade;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.repositories.EspecialidadeRepository;
import com.joaolucas.hospitalJJ.repositories.MedicoRepository;
import com.joaolucas.hospitalJJ.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;
    private final MedicoRepository medicoRepository;

    public List<EspecialidadeDTO> encontrarTodos(){
        return especialidadeRepository.findAll().stream().map(EspecialidadeDTO::new).toList();
    }

    public EspecialidadeDTO encontrarPorId(Long id){
        return new EspecialidadeDTO(especialidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + id)));
    }

    public EspecialidadeDTO criar(EspecialidadeDTO especialidadeDTO){
        if(!ValidacaoDeDados.validarDadosDaEspecialidade(especialidadeDTO)) throw new BadRequestException("Dados da especialidade são inválidos");

        Especialidade especialidade = new Especialidade();
        especialidade.setNome(especialidadeDTO.getNome());
        especialidade.setDescricao(especialidadeDTO.getDescricao());

        return new EspecialidadeDTO(especialidadeRepository.save(especialidade));
    }

    public EspecialidadeDTO atualizar(Long id, EspecialidadeDTO especialidadeDTO){
        if(!ValidacaoDeDados.validarDadosDaEspecialidade(especialidadeDTO)) throw new BadRequestException("Dados da especialidade são inválidos");
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + id));

        if(especialidadeDTO.getNome() != null) especialidade.setNome(especialidadeDTO.getNome());
        if(especialidadeDTO.getDescricao() != null) especialidade.setDescricao(especialidadeDTO.getDescricao());

        return new EspecialidadeDTO(especialidadeRepository.save(especialidade));
    }

    public void deletar(Long id){
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + id));
        especialidadeRepository.delete(especialidade);
    }

    public void adicionarMedico(Long especialidadeId, Long medicoId){
        Especialidade especialidade = especialidadeRepository.findById(especialidadeId).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + especialidadeId));
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new ResourceNotFoundException("Médico não foi encontrado com ID: " + medicoId));

        if(especialidade.getMedicos().contains(medico) || medico.getEspecialidades().contains(especialidade)) throw new BusinessLogicException("Médico já possui a dada especialidade");

        medico.getEspecialidades().add(especialidade);
        especialidade.getMedicos().add(medico);

        medicoRepository.save(medico);
        especialidadeRepository.save(especialidade);
    }

    public void removerMedico(Long especialidadeId, Long medicoId){
        Especialidade especialidade = especialidadeRepository.findById(especialidadeId).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + especialidadeId));
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new ResourceNotFoundException("Médico não foi encontrado com ID: " + medicoId));

        if(!especialidade.getMedicos().contains(medico) || !medico.getEspecialidades().contains(especialidade)) throw new BusinessLogicException("Médico não possui a dada especialidade");

        medico.getEspecialidades().remove(especialidade);
        especialidade.getMedicos().remove(medico);

        medicoRepository.save(medico);
        especialidadeRepository.save(especialidade);
    }

}
