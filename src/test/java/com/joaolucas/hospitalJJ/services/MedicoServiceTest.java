package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.MedicoDTO;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.repositories.MedicoRepository;
import com.joaolucas.hospitalJJ.services.MedicoService;
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
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;
    private MedicoService emTeste;
    private Medico medico;

    @BeforeEach
    void setUp() {
        emTeste = new MedicoService(medicoRepository);
        medico = new Medico();
    }

    @Test
    void deveEncontrarTodosMedicos() {
        when(medicoRepository.findAll()).thenReturn(List.of(medico));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new MedicoDTO(medico)));
    }

    @Test
    void deveEncontrarMedicoPorId() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new MedicoDTO(medico));
    }

    @Test
    void deveAtualizarMedico() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(medicoRepository.save(medico)).thenReturn(medico);

        MedicoDTO paraAtualizar = new MedicoDTO(medico);
        paraAtualizar.setNome("Davichi");

        var resultado = emTeste.atualizar(1L, paraAtualizar);

        assertThat(resultado).isNotNull();
        assertThat(medico.getNome()).isEqualTo("Davichi");
    }

    @Test
    void deveDeletarMedico() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        emTeste.deletar(1L);
        verify(medicoRepository, times(1)).delete(medico);
    }

}