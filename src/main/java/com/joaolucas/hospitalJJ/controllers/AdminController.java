package com.joaolucas.hospitalJJ.controllers;

import com.joaolucas.hospitalJJ.models.dto.AdminDTO;
import com.joaolucas.hospitalJJ.models.dto.MedicoDTO;
import com.joaolucas.hospitalJJ.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> encontrarTodos(){
        return ResponseEntity.ok(adminService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(adminService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> atualizar(@PathVariable Long id, @RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(adminService.atualizar(id, adminDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        adminService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
