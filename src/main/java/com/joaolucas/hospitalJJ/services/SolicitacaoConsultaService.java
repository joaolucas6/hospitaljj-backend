package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.exceptions.BadRequestException;
import com.joaolucas.hospitalJJ.exceptions.BusinessLogicException;
import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
import com.joaolucas.hospitalJJ.models.dto.SolicitacaoConsultaDTO;
import com.joaolucas.hospitalJJ.models.entities.*;
import com.joaolucas.hospitalJJ.models.enums.Status;
import com.joaolucas.hospitalJJ.repositories.*;
import com.joaolucas.hospitalJJ.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitacaoConsultaService {

    private final SolicitacaoConsultaRepository solicitacaoConsultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final ConsultaRepository consultaRepository;

    public List<SolicitacaoConsultaDTO> encontrarTodos(){
        return solicitacaoConsultaRepository.findAll().stream().map(SolicitacaoConsultaDTO::new).toList();
    }

    public SolicitacaoConsultaDTO encontrarPorId(Long id){
        return new SolicitacaoConsultaDTO(solicitacaoConsultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitação de consulta não foi encontrada com ID: " + id)));
    }

    public SolicitacaoConsultaDTO criar(SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        if(!ValidacaoDeDados.validarDadosDaSolicitacaoDeConsulta(solicitacaoConsultaDTO)) throw new BadRequestException("Dados da solicitação de consulta são inválidos");
        if(solicitacaoConsultaDTO.getMedicoId() == null || solicitacaoConsultaDTO.getPacienteId() == null) throw new BadRequestException("Id do médico e do paciente não devem ser nulos");

        Paciente paciente = pacienteRepository.findById(solicitacaoConsultaDTO.getPacienteId()).orElseThrow(() -> new ResourceNotFoundException("Paciente não foi encontrado com ID: " + solicitacaoConsultaDTO.getPacienteId()));
        Medico medico = medicoRepository.findById(solicitacaoConsultaDTO.getMedicoId()).orElseThrow(() -> new ResourceNotFoundException("Médico não foi encontrado com ID: " + solicitacaoConsultaDTO.getMedicoId()));
        Especialidade especialidade = especialidadeRepository.findById(solicitacaoConsultaDTO.getEspecialidadeId()).orElseThrow(() -> new ResourceNotFoundException("Especialidade não foi encontrada com ID: " + solicitacaoConsultaDTO.getEspecialidadeId()));

        if(!especialidade.getMedicos().contains(medico) || !medico.getEspecialidades().contains(especialidade)) throw new BusinessLogicException("Médico não domina a dada especialidade");

        // verificar se o horário de consulta está disponível
        if(!medico.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new BusinessLogicException("O horário não está disponível");

        SolicitacaoConsulta solicitacaoConsulta = new SolicitacaoConsulta();

        solicitacaoConsulta.setStatus(Status.PENDENTE);
        solicitacaoConsulta.setMotivoDaConsulta(solicitacaoConsultaDTO.getMotivoDaConsulta());
        solicitacaoConsulta.setInicioDaConsulta(solicitacaoConsultaDTO.getInicioDaConsulta());
        solicitacaoConsulta.setFimDaConsulta(solicitacaoConsulta.getFimDaConsulta());
        solicitacaoConsulta.setEspecialidade(especialidade);
        solicitacaoConsulta.setMedico(medico);
        solicitacaoConsulta.setPaciente(paciente);

        SolicitacaoConsulta solicitacaoConsultaSalva = solicitacaoConsultaRepository.save(solicitacaoConsulta);

        paciente.getSolicitacoesDeConsulta().add(solicitacaoConsultaSalva);
        medico.getSolicitacoesConsulta().add(solicitacaoConsultaSalva);
        especialidade.getSolicitacoesConsulta().add(solicitacaoConsultaSalva);

        pacienteRepository.save(paciente);
        medicoRepository.save(medico);

        return new SolicitacaoConsultaDTO(solicitacaoConsultaSalva);
    }

    public SolicitacaoConsultaDTO atualizar(Long solicitacaoId, SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow(() -> new ResourceNotFoundException("Solicitação de consulta não foi encontrada com ID: " + solicitacaoId));

        if(solicitacaoConsulta.getStatus() == Status.ACEITO || solicitacaoConsulta.getStatus() == Status.NEGADO) throw new BusinessLogicException("Não é possível atualizar a solicitação de consulta devido ao seu status");

        Medico medico = solicitacaoConsulta.getMedico();

        if(solicitacaoConsultaDTO.getInicioDaConsulta() != null && solicitacaoConsultaDTO.getFimDaConsulta() != null){

            // verificar se o horário de consulta está disponível
            if(!medico.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new BusinessLogicException("O horário não está disponível");

            solicitacaoConsulta.setInicioDaConsulta(solicitacaoConsultaDTO.getInicioDaConsulta());
            solicitacaoConsulta.setFimDaConsulta(solicitacaoConsultaDTO.getFimDaConsulta());
        }

        if(solicitacaoConsultaDTO.getMotivoDaConsulta() != null) solicitacaoConsulta.setMotivoDaConsulta(solicitacaoConsultaDTO.getMotivoDaConsulta());

        if(solicitacaoConsultaDTO.getEspecialidadeId() != null){
            Especialidade especialidadeDesejada = especialidadeRepository.findById(solicitacaoConsultaDTO.getEspecialidadeId()).orElseThrow();
            if(!medico.getEspecialidades().contains(especialidadeDesejada)) throw new BusinessLogicException("O médico não domina a dada especialidade");

            solicitacaoConsulta.getEspecialidade().getSolicitacoesConsulta().remove(solicitacaoConsulta);

            solicitacaoConsulta.setEspecialidade(especialidadeDesejada);
        }

        return new SolicitacaoConsultaDTO(solicitacaoConsultaRepository.save(solicitacaoConsulta));
    }

    public void deletar(Long id){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitação de consulta não foi encontrada com ID: " + id));

        if(solicitacaoConsulta.getStatus() != Status.PENDENTE) throw new BusinessLogicException("A solicitação de consulta não pode ser deletada devido ao seu status");

        solicitacaoConsultaRepository.delete(solicitacaoConsulta);
    }

    public void aceitarSolicitacao(Long solicitacaoId){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow(() -> new ResourceNotFoundException("Solicitação de consulta não foi encontrada com ID: " + solicitacaoId));
        Paciente paciente = solicitacaoConsulta.getPaciente();
        Medico medico = solicitacaoConsulta.getMedico();
        Especialidade especialidade = solicitacaoConsulta.getEspecialidade();

        solicitacaoConsulta.setStatus(Status.ACEITO);

        Consulta consulta = new Consulta();

        consulta.setDataDeCriacao(LocalDateTime.now());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setEspecialidade(solicitacaoConsulta.getEspecialidade());
        consulta.setStatus(Status.CONFIRMADO);
        consulta.setHorarioInicio(solicitacaoConsulta.getInicioDaConsulta());
        consulta.setHorarioTermino(solicitacaoConsulta.getFimDaConsulta());

        Consulta consultaSalva = consultaRepository.save(consulta);

        paciente.getConsultas().add(consultaSalva);
        medico.getConsultas().add(consultaSalva);
        especialidade.getConsultas().add(consultaSalva);

        pacienteRepository.save(paciente);
        medicoRepository.save(medico);
        especialidadeRepository.save(especialidade);
    }

    public void negarSolicitacao(Long solicitacaoId){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow(() -> new ResourceNotFoundException("Solicitação de consulta não foi encontrada com ID: " + solicitacaoId));
        solicitacaoConsulta.setStatus(Status.NEGADO);

        solicitacaoConsultaRepository.save(solicitacaoConsulta);
    }
}
