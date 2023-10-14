package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.PacienteDTO;
import com.joaolucas.hospitalJJ.models.dto.SolicitacaoConsultaDTO;
import com.joaolucas.hospitalJJ.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> encontrarTodos(){
        return ResponseEntity.ok(pacienteService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO){
        return ResponseEntity.ok(pacienteService.atualizar(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
