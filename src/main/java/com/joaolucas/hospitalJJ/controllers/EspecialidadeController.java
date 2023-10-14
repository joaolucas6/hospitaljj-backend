package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.EspecialidadeDTO;
import com.joaolucas.hospitalJJ.services.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidades")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<EspecialidadeDTO>> encontrarTodos(){
        return ResponseEntity.ok(especialidadeService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(especialidadeService.encontrarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EspecialidadeDTO> criar(@RequestBody EspecialidadeDTO especialidadeDTO){
        return ResponseEntity.ok(especialidadeService.criar(especialidadeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> atualizar(@PathVariable Long id, @RequestBody EspecialidadeDTO especialidadeDTO){
        return ResponseEntity.ok(especialidadeService.atualizar(id, especialidadeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        especialidadeService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{especialidadeId}/medicos/{medicoId}")
    public ResponseEntity<Void> adicionarMedico(@PathVariable Long especialidadeId, @PathVariable Long medicoId){
        especialidadeService.adicionarMedico(especialidadeId, medicoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{especialidadeId}/medicos/{medicoId}")
    public ResponseEntity<Void> removerMedico(@PathVariable Long especialidadeId, @PathVariable Long medicoId){
        especialidadeService.removerMedico(especialidadeId, medicoId);
        return ResponseEntity.ok().build();
    }

}
