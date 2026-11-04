package com.example.chamados.model;

import com.example.chamados.model.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;
    private String title;
    private String requester;
    private String sector;
    private Priority priority;
    private Status status;
}