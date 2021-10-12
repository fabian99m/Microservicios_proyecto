package com.pragma.serviciocliente.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicio;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicioUtils;
import com.pragma.serviciocliente.infraestructura.clientefeign.FotoRest;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.utils.FakerData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest

public class ClienteServicioTest {

    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    ClienteServicioUtils clienteServicioUtils;

    @Autowired
    FotoRest fotoRest; // cliente Rest Feign

    static Cliente cliente;

    @BeforeAll
    static void setUp() {
        cliente = FakerData.getCliente();
    }

    @Test
    @Order(1)
    void guardarCliente() {

        // datos de cliente
        ClienteEntidad clienteEntidad = clienteServicio.guadarCliente(cliente);
        assertNotNull(clienteEntidad);
        assertEquals(cliente.getNombres(), clienteEntidad.getNombres());
        assertEquals(cliente.getApellidos(), clienteEntidad.getApellidos());
        assertEquals(cliente.getEdad(), clienteEntidad.getEdad());
        assertEquals(cliente.getTipoId(), clienteEntidad.getTipoId());
        assertEquals(cliente.getNumeroId(), clienteEntidad.getNumeroId());

        // foto de cliente
        Foto foto = clienteServicioUtils.getFoto(cliente);
        assertEquals(cliente.getFoto(),foto.getFoto());
    }

    @Test
    @Order(2)
    void listarClientes() {
        List<Cliente> clientes = clienteServicio.listarClientes();
        assertFalse(clientes.isEmpty());
        Cliente clientetest = clientes.stream().filter(cl ->
                        cl.getTipoId().equals(cliente.getTipoId()) && cl.getNumeroId().equals(cliente.getNumeroId()))
                .collect(Collectors.toList()).get(0);
        assertEquals(clientetest, cliente);
    }

    @Test
    @Order(3)
    void findByEdadGreaterThanEqual() {
        List<Cliente> clientes = clienteServicio.findByEdadGreaterThanEqual(cliente.getEdad());
        assertFalse(clientes.isEmpty());
    }

    @Test
    @Order(4)
    void findByTipoIdAndNumeroId() {
        Cliente clienteBd = clienteServicio.findByTipoIdAndNumeroId(cliente.getTipoId(), cliente.getNumeroId());
        assertEquals(cliente, clienteBd);
    }

    @Test
    @Order(5)
    void eliminarCliente() {
        assertNotNull(clienteServicio.findByTipoIdAndNumeroId(cliente.getTipoId(), cliente.getNumeroId()));
        assertNotNull(clienteServicioUtils.getFoto(cliente));

        clienteServicio.eliminarCliente(cliente);

        assertNull(clienteServicio.findByTipoIdAndNumeroId(cliente.getTipoId(), cliente.getNumeroId()));
        assertNull(clienteServicioUtils.getFoto(cliente));

    }

}
