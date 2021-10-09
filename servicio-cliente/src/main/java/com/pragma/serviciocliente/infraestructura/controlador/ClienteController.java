package com.pragma.serviciocliente.infraestructura.controlador;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicio;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicioUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    protected ClienteServicioUtils clienteServicioUtils;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> guardarCliente(Cliente cliente, @RequestParam("fotoFile") MultipartFile file)  {
        try {
            String fotoBase64 = Base64.getEncoder().encodeToString(file.getBytes());
            cliente.setFoto(fotoBase64);
        } catch (IOException e) {cliente.setFoto("");}

        if (clienteServicioUtils.existId(cliente.getTipoId(), cliente.getNumeroId())) {
            return new ResponseEntity<>(ResponseMessage.builder().code("400").response("Cliente ya registrado.").build(), HttpStatus.BAD_REQUEST);
        }
        clienteServicio.guadarCliente(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente() {
        List<Cliente> clientes = clienteServicio.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/edad/{edadString}")
    public ResponseEntity<List<Cliente>> filtroEdad(@PathVariable String edadString) {
        if(NumberUtils.isDigits(edadString)){
           int edad = Integer.parseInt(edadString);
            List<Cliente> clientes = clienteServicio.findByEdadGreaterThanEqual(edad);
            if (clientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clientes, HttpStatus.FOUND);
        }
        return new ResponseEntity("Edad no válida.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id/{tipoId}/{numeroId}")
    public ResponseEntity<Cliente> filtroId(@PathVariable String tipoId, @PathVariable String numeroId) {
        Cliente cliente = clienteServicio.findByTipoIdAndNumeroId(tipoId, numeroId);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.FOUND);
    }

}
