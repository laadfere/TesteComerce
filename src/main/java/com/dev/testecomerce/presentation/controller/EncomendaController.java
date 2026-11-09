package com.dev.testecomerce.presentation.controller;

import com.dev.testecomerce.application.EncomendaService;
import com.dev.testecomerce.domain.model.Encomenda;
import com.dev.testecomerce.domain.model.StatusEncomenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/encomendas")
public class EncomendaController {

    @Autowired
    private EncomendaService service;

    @PostMapping
    public Encomenda criar(@RequestBody Encomenda encomenda) {
        return service.criarEncomenda(encomenda);
    }

    @GetMapping
    public List<Encomenda> buscarTodas() {
        return service.listarTodas();
    }

    @PatchMapping("/{id}/status")
    public Encomenda atualizarStatus(@PathVariable Long id, @RequestParam StatusEncomenda status) {
        return service.atualizarStatus(id, status);
    }
}