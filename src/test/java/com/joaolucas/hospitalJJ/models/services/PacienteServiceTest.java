package com.joaolucas.hospitalJJ.models.services;

import com.joaolucas.hospitalJJ.models.dto.PacienteDTO;
import com.joaolucas.hospitalJJ.models.entities.Paciente;
import com.joaolucas.hospitalJJ.models.repositories.PacienteRepository;
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
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;
    private PacienteService emTeste;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        emTeste = new PacienteService(pacienteRepository);
        paciente = new Paciente();
    }

    @Test
    void deveRetornarTodosOsPacientes() {
        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new PacienteDTO(paciente)));
    }

    @Test
    void deveRetornarPacientePorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new PacienteDTO(paciente));
    }

    @Test
    void deveAtualizarPaciente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        PacienteDTO paraAtualizar = new PacienteDTO(paciente);
        paraAtualizar.setNome("Baby Keem");

        var resultado = emTeste.atualizar(1L, paraAtualizar);

        assertThat(resultado).isNotNull();
        assertThat(paciente.getNome()).isEqualTo("Baby Keem");
    }

    @Test
    void deveDeletarPaciente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        emTeste.deletar(1L);
        verify(pacienteRepository, times(1)).delete(paciente);
    }
}