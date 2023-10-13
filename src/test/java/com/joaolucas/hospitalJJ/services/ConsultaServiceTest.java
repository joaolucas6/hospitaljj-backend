package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.ConsultaDTO;
import com.joaolucas.hospitalJJ.models.entities.Consulta;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.enums.Status;
import com.joaolucas.hospitalJJ.repositories.ConsultaRepository;
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
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;
    private ConsultaService emTeste;
    private Consulta consulta;
    private Medico medico;

    @BeforeEach
    void setUp() {
        emTeste = new ConsultaService(consultaRepository);
        consulta = new Consulta();
    }

    @Test
    void deveRetornarTodasAsConsultas() {
        when(consultaRepository.findAll()).thenReturn(List.of(consulta));

        var resultado = emTeste.encontrarTodos();

        assertThat(resultado).isEqualTo(List.of(new ConsultaDTO(consulta)));
    }

    @Test
    void deveRetornarConsultaPorId() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        var resultado = emTeste.encontrarPorId(1L);

        assertThat(resultado).isEqualTo(new ConsultaDTO(consulta));
    }

    @Test
    void deveAtualizarConsulta() {
        medico = new Medico();
        consulta.setMedico(medico);

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        ConsultaDTO paraAtualizar = new ConsultaDTO(consulta);
        paraAtualizar.setObservacoesMedica("muito doente");

        var resultado = emTeste.atualizar(1L, paraAtualizar);

        assertThat(resultado).isNotNull();
        assertThat(consulta.getObservacoesMedica()).isEqualTo("muito doente");
    }

    @Test
    void deveDeletarConsulta() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        emTeste.deletar(1L);

        verify(consultaRepository, times(1)).delete(consulta);
    }

    @Test
    void deveCancelarConsulta() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        consulta.setStatus(Status.CONFIRMADO);


        emTeste.cancelarConsulta(1L);

        assertThat(consulta.getStatus()).isEqualTo(Status.CANCELADO);
    }

    @Test
    void deveConcluirConsulta() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        consulta.setStatus(Status.CONFIRMADO);

        emTeste.concluirConsulta(1L);

        assertThat(consulta.getStatus()).isEqualTo(Status.CONCLUIDO);
    }
}