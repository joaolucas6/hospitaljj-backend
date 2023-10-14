package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.MedicoDTO;
import com.joaolucas.hospitalJJ.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> encontrarTodos(){
        return ResponseEntity.ok(medicoService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizar(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO){
        return ResponseEntity.ok(medicoService.atualizar(id, medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        medicoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
