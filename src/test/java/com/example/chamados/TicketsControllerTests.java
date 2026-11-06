package com.example.chamados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveListarTodosOsChamados() throws Exception {
        mockMvc.perform(get("/chamados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    void deveBuscarChamadoPorId() throws Exception {
        mockMvc.perform(get("/chamados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Computador não liga"));
    }

    @Test
    void deveRetornar404ParaIdInexistente() throws Exception {
        mockMvc.perform(get("/chamados/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveFiltrarSetorSemDiferenciarMaiusculas() throws Exception {
        mockMvc.perform(get("/chamados/setor").param("nome", "financeiro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].setor", everyItem(equalTo("Financeiro"))));
    }
}
