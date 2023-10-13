package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.ConsultaDTO;
import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.enums.Status;
import com.joaolucas.hospitalJJ.repositories.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public List<ConsultaDTO> encontrarTodos(){
        return consultaRepository.findAll().stream().map(ConsultaDTO::new).toList();
    }

    public ConsultaDTO encontrarPorId(Long id){
        return new ConsultaDTO(consultaRepository.findById(id).orElseThrow());
    }

    public ConsultaDTO atualizar(Long id, ConsultaDTO consultaDTO){
        Consulta consulta = consultaRepository.findById(id).orElseThrow();

        if(consultaDTO.getPreco() != null) consulta.setPreco(consultaDTO.getPreco());
        if(consultaDTO.getReceitaMedica() != null) consulta.setReceitaMedica(consultaDTO.getReceitaMedica());
        if(consultaDTO.getObservacoesMedica() != null) consulta.setObservacoesMedica(consultaDTO.getObservacoesMedica());
        if(consultaDTO.getStatus() != null) consulta.setStatus(consultaDTO.getStatus());

        if(consultaDTO.getHorarioInicio() != null && consultaDTO.getHorarioTermino() != null){

            Medico medico = consulta.getMedico();

            // verificar se o horário de consulta está disponível
            if(!medico.getConsultas().stream().filter(c -> consultaDTO.getHorarioInicio().isAfter(c.getHorarioInicio()) && consultaDTO.getHorarioTermino().isBefore(c.getHorarioTermino())).toList().isEmpty()) throw new RuntimeException();
            if(!medico.getSolicitacoesConsulta().stream().filter(s -> consultaDTO.getHorarioInicio().isAfter(s.getInicioDaConsulta()) && consultaDTO.getHorarioTermino().isBefore(s.getFimDaConsulta())).toList().isEmpty()) throw new RuntimeException();


            consulta.setHorarioInicio(consultaDTO.getHorarioInicio());
            consulta.setHorarioTermino(consultaDTO.getHorarioTermino());
        }

        return new ConsultaDTO(consultaRepository.save(consulta));
    }

    public void deletar(Long id){
        Consulta consulta = consultaRepository.findById(id).orElseThrow();
        consultaRepository.delete(consulta);
    }

    public void cancelarConsulta(Long consultaId){
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow();
        consulta.setStatus(Status.CANCELADO);
    }

    public void concluirConsulta(Long consultaId){
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow();
        consulta.setStatus(Status.CONCLUIDO);
    }
}
