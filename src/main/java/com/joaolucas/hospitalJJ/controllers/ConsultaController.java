package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.ConsultaDTO;
import com.joaolucas.hospitalJJ.services.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> encontrarTodos(){
        return ResponseEntity.ok(consultaService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizar(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO){
        return ResponseEntity.ok(consultaService.atualizar(id, consultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        consultaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{consultaId}/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long consultaId){
        consultaService.cancelarConsulta(consultaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{consultaId}/concluir")
    public ResponseEntity<Void> concluirConsulta(@PathVariable Long consultaId){
        consultaService.concluirConsulta(consultaId);
        return ResponseEntity.ok().build();
    }

}
