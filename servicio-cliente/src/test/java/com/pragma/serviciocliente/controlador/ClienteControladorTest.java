package com.pragma.serviciocliente.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.utils.FakerData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControladorTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

     static Cliente cliente=FakerData.getCliente();



    @Test
    @Order(1)
    void guardarClienteTest() throws Exception {
        mvc.perform(multipart("/cliente")
                        .file("fotoFile", "foto".getBytes(StandardCharsets.UTF_8))
                        .param("clienteJson", objectMapper.writeValueAsString(cliente))
                ).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    @Order(2)
    void listarClientes() throws Exception {
        mvc.perform(get("/cliente")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)
                );
    }

    @Test
    @Order(3)
    void filtroEdad() throws Exception {
            mvc.perform(get("/cliente/edad/"+cliente.getEdad()+"")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.FOUND.value()))
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }

    @Test
    @Order(4)
    void filtroId() throws Exception {
        mvc.perform(get("/cliente/id/"+cliente.getTipoId()+"/"+cliente.getNumeroId()+"")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombres").value(cliente.getNombres()));
    }

    /* @Test
    @Order(5)
    void eliminarCliente() throws Exception {
        mvc.perform(delete("/cliente/id/"+cliente.getTipoId()+"/"+cliente.getNumeroId()+""))
                .andExpect(status().is(HttpStatus.OK.value()));
    } */

}
