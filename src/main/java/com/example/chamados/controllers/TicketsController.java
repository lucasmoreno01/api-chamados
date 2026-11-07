package com.example.chamados.controllers;

import com.example.chamados.data.TicketsData;
import com.example.chamados.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.chamados.model.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.chamados.model.Status;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.EnumMap;
import java.util.Map;
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

    @GetMapping("/prioridade")
    public List<Ticket> filtrarPorPrioridade(@RequestParam String nivel) {
        Priority prioridade;

        try {
            prioridade = Priority.valueOf(nivel.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Prioridade inválida. Use BAIXA, MEDIA ou ALTA."
            );
        }

        return TicketsData.tickets.stream()
                .filter(ticket -> ticket.getPrioridade() == prioridade)
                .toList();
    }

    @GetMapping("/busca")
    public List<Ticket> buscarPorTitulo(@RequestParam String texto) {
        String termo = texto.trim().toLowerCase();

        return TicketsData.tickets.stream()
                .filter(ticket -> ticket.getTitulo().toLowerCase().contains(termo))
                .toList();
    }
    @GetMapping("/criticos")
    public List<Ticket> listarCriticos() {
        return TicketsData.tickets.stream()
                .filter(ticket -> ticket.getPrioridade() == Priority.ALTA)
                .filter(ticket -> ticket.getStatus() != Status.RESOLVIDO)
                .toList();
    }

    @GetMapping("/resumo")
    public Map<Status, Long> resumoPorStatus() {
        Map<Status, Long> resumo = new EnumMap<>(Status.class);

        for (Status status : Status.values()) {
            long quantidade = TicketsData.tickets.stream()
                    .filter(ticket -> ticket.getStatus() == status)
                    .count();
            resumo.put(status, quantidade);
        }

        return resumo;
    }

    @PostMapping
    public ResponseEntity<Ticket> cadastrar(@RequestBody Ticket novo) {
        if (novo.getTitulo() == null || novo.getTitulo().isBlank()
                || novo.getSolicitante() == null || novo.getSolicitante().isBlank()
                || novo.getSetor() == null || novo.getSetor().isBlank()
                || novo.getPrioridade() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe titulo, solicitante, setor e prioridade."
            );
        }

        novo.setId(TicketsData.nextID++);
        novo.setStatus(Status.ABERTO);
        TicketsData.tickets.add(novo);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }
}
