package com.example.chamados.controllers;

import com.example.chamados.data.TicketsData;
import com.example.chamados.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class TicketsController {

    @GetMapping
    public List<Ticket> listarTodos() {
        return TicketsData.tickets;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id) {
        return TicketsData.tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/setor")
    public List<Ticket> filtrarPorSetor(@RequestParam String nome) {
        return TicketsData.tickets.stream()
                .filter(ticket -> ticket.getSetor().equalsIgnoreCase(nome.trim()))
                .toList();
    }
}
