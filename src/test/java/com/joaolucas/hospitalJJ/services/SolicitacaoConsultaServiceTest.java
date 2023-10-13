package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.SolicitacaoConsultaDTO;
import com.joaolucas.hospitalJJ.models.entities.*;
import com.joaolucas.hospitalJJ.models.enums.Status;
import com.joaolucas.hospitalJJ.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitacaoConsultaServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private MedicoRepository medicoRepository;
    @Mock
    private EspecialidadeRepository especialidadeRepository;
    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private SolicitacaoConsultaRepository solicitacaoConsultaRepository;
    private SolicitacaoConsultaService emTeste;
    private SolicitacaoConsulta solicitacaoConsulta;
    private Medico medico;
    private Paciente paciente;
    private Especialidade especialidade;
    private Consulta consulta;

    @BeforeEach
    void setUp() {
        emTeste = new SolicitacaoConsultaService(solicitacaoConsultaRepository, pacienteRepository, medicoRepository, especialidadeRepository, consultaRepository);
        solicitacaoConsulta = new SolicitacaoConsulta();
    }

    @Test
    void deveRetornarTodasAsSolicitacoesDeConsulta() {
        when(solicitacaoConsultaRepository.findAll()).thenReturn(List.of(solicitacaoConsulta));

        var resultado = emTeste.encontrarTodos();

        assertThat(resultado).isEqualTo(List.of(new SolicitacaoConsultaDTO(solicitacaoConsulta)));
    }

    @Test
    void deveRetornarSolicitacaoDeConsultaPorId() {
        when(solicitacaoConsultaRepository.findById(1L)).thenReturn(Optional.of(solicitacaoConsulta));

        var resultado = emTeste.encontrarPorId(1L);

        assertThat(resultado).isEqualTo(new SolicitacaoConsultaDTO(solicitacaoConsulta));
    }

    @Test
    void deveCriarSolicitacaoDeConsulta() {
        medico = new Medico();
        paciente = new Paciente();
        especialidade = new Especialidade();

        medico.setId(1L);
        paciente.setId(1L);
        especialidade.setId(1L);

        medico.getEspecialidades().add(especialidade);
        especialidade.getMedicos().add(medico);

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(solicitacaoConsultaRepository.save(solicitacaoConsulta)).thenReturn(solicitacaoConsulta);

        SolicitacaoConsultaDTO solicitacaoConsultaDTO = new SolicitacaoConsultaDTO(solicitacaoConsulta);
        solicitacaoConsultaDTO.setPacienteId(1L);
        solicitacaoConsultaDTO.setMedicoId(1L);
        solicitacaoConsultaDTO.setEspecialidadeId(1L);

        var resultado = emTeste.criar(solicitacaoConsultaDTO);

        assertThat(resultado).isNotNull();
        assertThat(paciente.getSolicitacoesDeConsulta().contains(solicitacaoConsulta) && medico.getSolicitacoesConsulta().contains(solicitacaoConsulta) && especialidade.getSolicitacoesConsulta().contains(solicitacaoConsulta)).isTrue();
    }

    @Test
    void deveAtualizarSolicitacaoDeConsulta() {
        medico = new Medico();
        paciente = new Paciente();

        when(solicitacaoConsultaRepository.findById(1L)).thenReturn(Optional.of(solicitacaoConsulta));
        when(solicitacaoConsultaRepository.save(solicitacaoConsulta)).thenReturn(solicitacaoConsulta);


        SolicitacaoConsultaDTO paraAtualizar = new SolicitacaoConsultaDTO(solicitacaoConsulta);
        paraAtualizar.setMotivoDaConsulta("muita dor");


        var resultado = emTeste.atualizar(1L, paraAtualizar);

        assertThat(resultado).isNotNull();
        assertThat(solicitacaoConsulta.getMotivoDaConsulta()).isEqualTo("muita dor");
    }

    @Test
    void deveDeletarSolicitacaoDeConsulta() {
        when(solicitacaoConsultaRepository.findById(1L)).thenReturn(Optional.of(solicitacaoConsulta));

        solicitacaoConsulta.setStatus(Status.PENDENTE);

        emTeste.deletar(1L);

        verify(solicitacaoConsultaRepository, times(1)).delete(solicitacaoConsulta);
    }

    @Test
    void deveAceitarSolicitacaoDeConsulta() {
        consulta = new Consulta();
        paciente = new Paciente();
        especialidade = new Especialidade();
        medico = new Medico();


        when(solicitacaoConsultaRepository.findById(1L)).thenReturn(Optional.of(solicitacaoConsulta));
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        solicitacaoConsulta.setPaciente(paciente);
        solicitacaoConsulta.setMedico(medico);
        solicitacaoConsulta.setEspecialidade(especialidade);

        emTeste.aceitarSolicitacao(1L);
        assertThat(paciente.getConsultas().contains(consulta) && medico.getConsultas().contains(consulta) && especialidade.getConsultas().contains(consulta)).isTrue();
    }

    @Test
    void deveNegarSolicitacaoDeConsulta() {
        when(solicitacaoConsultaRepository.findById(1L)).thenReturn(Optional.of(solicitacaoConsulta));
        emTeste.negarSolicitacao(1L);
        assertThat(solicitacaoConsulta.getStatus()).isEqualTo(Status.NEGADO);
    }
}