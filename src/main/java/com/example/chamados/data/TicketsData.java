package com.example.chamados.data;

import com.example.chamados.model.Priority;
import com.example.chamados.model.Status;
import com.example.chamados.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsData {

    public static final List<Ticket> tickets = new ArrayList<>();

    public static Long nextID = 11L;

    static {
        tickets.add(new Ticket(1L, "Computador não liga", "Lucas", "Financeiro", Priority.HIGH, Status.OPEN));
        tickets.add(new Ticket(2L, "Impressora sem conexão", "Mariana", "RH", Priority.MEDIUM, Status.IN_PROGRESS));
        tickets.add(new Ticket(3L, "Solicitação de acesso ao sistema", "Pedro", "Comercial", Priority.LOW, Status.CLOSED));
        tickets.add(new Ticket(4L, "Internet instável", "Ana", "TI", Priority.HIGH, Status.IN_PROGRESS));
        tickets.add(new Ticket(5L, "Monitor com defeito", "Carlos", "Financeiro", Priority.MEDIUM, Status.OPEN));
        tickets.add(new Ticket(6L, "Problema no login", "Julia", "RH", Priority.HIGH, Status.CLOSED));
        tickets.add(new Ticket(7L, "Instalação de software", "Rafael", "Comercial", Priority.LOW, Status.OPEN));
        tickets.add(new Ticket(8L, "Teclado não funciona", "Beatriz", "TI", Priority.MEDIUM, Status.IN_PROGRESS));
        tickets.add(new Ticket(9L, "Erro no sistema financeiro", "Marcos", "Financeiro", Priority.HIGH, Status.OPEN));
        tickets.add(new Ticket(10L, "Configuração de impressora", "Fernanda", "RH", Priority.LOW, Status.CLOSED));
    }
}