package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.dto.EspecialidadeDTO;
import com.joaolucas.hospitalJJ.models.entities.Especialidade;
import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.repositories.EspecialidadeRepository;
import com.joaolucas.hospitalJJ.repositories.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository especialidadeRepository;
    @Mock
    private MedicoRepository medicoRepository;
    private EspecialidadeService emTeste;
    private Especialidade especialidade;
    private Medico medico;

    @BeforeEach
    void setUp() {
        emTeste = new EspecialidadeService(especialidadeRepository, medicoRepository);
        especialidade = new Especialidade();
    }

    @Test
    void deveRetornarTodasAsEspecialidades() {
        when(especialidadeRepository.findAll()).thenReturn(List.of(especialidade));

        var resultado = emTeste.encontrarTodos();

        assertThat(resultado).isEqualTo(List.of(new EspecialidadeDTO(especialidade)));
    }

    @Test
    void deveRetornarEspecialidadePorId() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));

        var resultado = emTeste.encontrarPorId(1L);

        assertThat(resultado).isEqualTo(new EspecialidadeDTO(especialidade));
    }

    @Test
    void deveCriarEspecialidade() {
        when(especialidadeRepository.save(especialidade)).thenReturn(especialidade);

        var resultado = emTeste.criar(new EspecialidadeDTO(especialidade));

        assertThat(resultado).isNotNull();
    }

    @Test
    void deveAtualizarEspecialidade() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(especialidadeRepository.save(especialidade)).thenReturn(especialidade);

        EspecialidadeDTO paraAtualizar = new EspecialidadeDTO(especialidade);
        paraAtualizar.setNome("oftalmologia");

        var resultado = emTeste.atualizar(1L, paraAtualizar);

        assertThat(resultado).isNotNull();
        assertThat(especialidade.getNome()).isEqualTo("oftalmologia");
    }

    @Test
    void deveDeletarEspecialidade() {
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        emTeste.deletar(1L);

        verify(especialidadeRepository, times(1)).delete(especialidade);
    }

    @Test
    void deveAdicionarMedicoNaEspecialidade() {
        medico = new Medico();
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        emTeste.adicionarMedico(1L, 1L);

        assertThat(especialidade.getMedicos().contains(medico) && medico.getEspecialidades().contains(especialidade)).isTrue();
    }

    @Test
    void deveRemoverMedicoDaEspecialidade() {
        medico = new Medico();
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(especialidade));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        medico.getEspecialidades().add(especialidade);
        especialidade.getMedicos().add(medico);

        emTeste.removerMedico(1L, 1L);

        assertThat(especialidade.getMedicos().contains(medico) && medico.getEspecialidades().contains(especialidade)).isFalse();
    }
}