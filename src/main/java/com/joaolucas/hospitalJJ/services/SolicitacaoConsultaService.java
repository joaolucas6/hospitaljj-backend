package com.joaolucas.hospitalJJ.services;

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
        return new SolicitacaoConsultaDTO(solicitacaoConsultaRepository.findById(id).orElseThrow());
    }

    public SolicitacaoConsultaDTO criar(SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        if(!ValidacaoDeDados.validarDadosDaSolicitacaoDeConsulta(solicitacaoConsultaDTO)) throw new RuntimeException();
        if(solicitacaoConsultaDTO.getMedicoId() == null || solicitacaoConsultaDTO.getPacienteId() == null) throw new RuntimeException();

        Paciente paciente = pacienteRepository.findById(solicitacaoConsultaDTO.getPacienteId()).orElseThrow();
        Medico medico = medicoRepository.findById(solicitacaoConsultaDTO.getMedicoId()).orElseThrow();
        Especialidade especialidade = especialidadeRepository.findById(solicitacaoConsultaDTO.getEspecialidadeId()).orElseThrow();

        if(!especialidade.getMedicos().contains(medico) || !medico.getEspecialidades().contains(especialidade)) throw new RuntimeException();

        // verificar se o horário de consulta está disponível
        if(!medico.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new RuntimeException();
        if(!paciente.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new RuntimeException();

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
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow();

        if(solicitacaoConsulta.getStatus() == Status.ACEITO || solicitacaoConsulta.getStatus() == Status.NEGADO) throw new RuntimeException();

        Paciente paciente = solicitacaoConsulta.getPaciente();
        Medico medico = solicitacaoConsulta.getMedico();

        if(solicitacaoConsulta.getInicioDaConsulta() != null && solicitacaoConsultaDTO.getFimDaConsulta() != null){

            // verificar se o horário de consulta está disponível
            if(!medico.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new RuntimeException();
            if(!paciente.getConsultas().stream().filter(c -> solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(c.getHorarioInicio()) && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new RuntimeException();

            solicitacaoConsulta.setInicioDaConsulta(solicitacaoConsultaDTO.getInicioDaConsulta());
            solicitacaoConsulta.setFimDaConsulta(solicitacaoConsultaDTO.getFimDaConsulta());
        }

        if(solicitacaoConsultaDTO.getMotivoDaConsulta() != null) solicitacaoConsulta.setMotivoDaConsulta(solicitacaoConsultaDTO.getMotivoDaConsulta());

        if(solicitacaoConsultaDTO.getEspecialidadeId() != null){
            Especialidade especialidadeDesejada = especialidadeRepository.findById(solicitacaoConsultaDTO.getEspecialidadeId()).orElseThrow();
            if(!medico.getEspecialidades().contains(especialidadeDesejada)) throw new RuntimeException();

            solicitacaoConsulta.getEspecialidade().getSolicitacoesConsulta().remove(solicitacaoConsulta);

            solicitacaoConsulta.setEspecialidade(especialidadeDesejada);
        }

        return new SolicitacaoConsultaDTO(solicitacaoConsultaRepository.save(solicitacaoConsulta));
    }

    public void deletar(Long id){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(id).orElseThrow();

        if(solicitacaoConsulta.getStatus() != Status.PENDENTE) throw new RuntimeException();

        solicitacaoConsultaRepository.delete(solicitacaoConsulta);
    }

    public void aceitarSolicitacao(Long solicitacaoId){
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow();
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
        SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaRepository.findById(solicitacaoId).orElseThrow();
        solicitacaoConsulta.setStatus(Status.NEGADO);

        solicitacaoConsultaRepository.save(solicitacaoConsulta);
    }
}
