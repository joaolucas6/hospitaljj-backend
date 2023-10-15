package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.records.AuthenticationRequest;
import com.joaolucas.hospitalJJ.models.records.RegisterRequest;
import com.joaolucas.hospitalJJ.services.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/autenticacao")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/registrar/paciente")
    public ResponseEntity<String> registrarPaciente(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(autenticacaoService.registrarPaciente(request));
    }

    @PostMapping("/registrar/medico")
    public ResponseEntity<String> registrarMedico(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(autenticacaoService.registrarMedico(request));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticar(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(autenticacaoService.autenticar(request));
    }

}
