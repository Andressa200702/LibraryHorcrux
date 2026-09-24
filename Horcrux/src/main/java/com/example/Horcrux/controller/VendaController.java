package com.example.Horcrux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Horcrux.models.entity.VendaEntity;
import com.example.Horcrux.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<VendaEntity> listar() {
        return vendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaEntity> buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<VendaEntity> buscarPorCliente(@PathVariable Long clienteId) {
        return vendaService.buscarPorCliente(clienteId);
    }

    @PostMapping
    public ResponseEntity<VendaEntity> criar(@RequestBody VendaEntity venda) {
        try {
            VendaEntity salva = vendaService.registrarVenda(venda);
            return ResponseEntity.status(HttpStatus.CREATED).body(salva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}