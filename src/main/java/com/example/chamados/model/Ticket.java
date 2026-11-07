package com.example.chamados.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;
    private String titulo;
    private String solicitante;
    private String setor;
    private Priority prioridade;
    private Status status;
}
