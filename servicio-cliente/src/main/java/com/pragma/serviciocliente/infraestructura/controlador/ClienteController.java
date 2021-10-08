package com.pragma.serviciocliente.infraestructura.controlador;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente){
        System.out.println(cliente);
        if(!clienteServicio.isUniqueId(cliente.getTipoId(),cliente.getNumeroId())){
            clienteServicio.guadarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }  else {
            return ResponseEntity.badRequest().body("Cliente ya registrado.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente(){
        List<Cliente> clientes = clienteServicio.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/filtrar/edad")
    public ResponseEntity<List<Cliente>> filtroEdad(@RequestParam(name = "edad") int edad){
        var clientes = clienteServicio.findByEdadGreaterThanEqual(edad);
        if (clientes.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(clientes, HttpStatus.FOUND);
    }

    @GetMapping("/filtrar/id")
    public ResponseEntity<Cliente> filtroId(@RequestParam(name = "tipoId") String tipoId, @RequestParam(name = "numeroId") String numeroId){
        var a= clienteServicio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

}
