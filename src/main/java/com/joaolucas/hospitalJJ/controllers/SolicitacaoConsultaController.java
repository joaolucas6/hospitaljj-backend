package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.SolicitacaoConsultaDTO;
import com.joaolucas.hospitalJJ.services.SolicitacaoConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitacoes-de-consulta")
@RequiredArgsConstructor
public class SolicitacaoConsultaController {

    private final SolicitacaoConsultaService solicitacaoConsultaService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoConsultaDTO>> encontrarTodos(){
        return ResponseEntity.ok(solicitacaoConsultaService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoConsultaDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(solicitacaoConsultaService.encontrarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SolicitacaoConsultaDTO> criar(@RequestBody SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        return ResponseEntity.ok(solicitacaoConsultaService.criar(solicitacaoConsultaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoConsultaDTO> atualizar(@PathVariable Long id, @RequestBody SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        return ResponseEntity.ok(solicitacaoConsultaService.atualizar(id, solicitacaoConsultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        solicitacaoConsultaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{solicitacaoId}/aceitar-solicitacao")
    public ResponseEntity<Void> aceitarSolicitacao(@PathVariable Long solicitacaoId){
        solicitacaoConsultaService.aceitarSolicitacao(solicitacaoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{solicitacaoId}/negar-solicitacao")
    public ResponseEntity<Void> negarSolicitacao(@PathVariable Long solicitacaoId){
        solicitacaoConsultaService.negarSolicitacao(solicitacaoId);
        return ResponseEntity.ok().build();
    }

}
