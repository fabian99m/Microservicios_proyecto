package com.pragma.serviciocliente.infraestructura.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicio;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicioUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    protected ClienteServicioUtils clienteServicioUtils;


    @ApiOperation("Guardar cliente con foto como archivo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente guardado."),
            @ApiResponse(code = 400, message = "Cliente ya registrado."),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> guardarCliente(@RequestParam String  clienteJson, @RequestParam("fotoFile") MultipartFile fotoFile) throws IOException {
        Cliente cliente = new ObjectMapper().readValue(clienteJson, Cliente.class);
        System.out.println(cliente);
        String fotoBase64 = Base64.getEncoder().encodeToString(fotoFile.getBytes());

        if (clienteServicioUtils.existId(cliente.getTipoId(), cliente.getNumeroId())) {
            return new ResponseEntity<>(ResponseMessage.builder().code("400").response("Cliente ya registrado.").build(), HttpStatus.BAD_REQUEST);
        }
        clienteServicio.guadarCliente(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Elimar cliente por tipo y número de identificación")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente eliminado."),
            @ApiResponse(code = 400, message = "Cliente no encontrado."),
    })
    @DeleteMapping("/id/{tipoId}/{numeroId}")
    public ResponseEntity<?> eliminarCliente(@PathVariable String tipoId, @PathVariable String numeroId) {
        Cliente clienteBd = clienteServicio.findByTipoIdAndNumeroId(tipoId, numeroId);
        if (clienteBd == null) {
            return new ResponseEntity<>(ResponseMessage.builder().code("404").response("Cliente no encontrado.").build(), HttpStatus.NOT_FOUND);
        }
        clienteServicio.eliminarCliente(clienteBd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Actualizar cliente por tipo y número de identificación")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente actualizado."),
            @ApiResponse(code = 400, message = "Cliente no encontrado."),
    })
    @PutMapping("/id/{tipoId}/{numeroId}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String tipoId, @PathVariable String numeroId,
                                               @ModelAttribute Cliente cliente, @RequestParam("fotoFile") MultipartFile fotoFile) {
        Cliente clienteBd = clienteServicio.findByTipoIdAndNumeroId(tipoId, numeroId);
        if (clienteBd != null) {
            try {
                String fotoBase64 = Base64.getEncoder().encodeToString(fotoFile.getBytes());
                cliente.setFoto(fotoBase64);
            } catch (IOException e) {
                cliente.setFoto("Foto no disponible.");
            }
            clienteServicio.actualizarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseMessage.builder().code("404").response("Cliente no encontrado.").build(), HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Listar todos los clientes registrados")
    @ApiResponses({
            @ApiResponse(code = 302, message = "Cliente encontrados."),
            @ApiResponse(code = 404, message = "Clientes no encontrados."),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> listarCliente() {
        List<Cliente> clientes = clienteServicio.listarClientes();
        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientes, HttpStatus.FOUND);
    }

    @ApiOperation("Filtrar clientes por edad, mayor o igual")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Edad no válida"),
            @ApiResponse(code = 302, message = "Clientes encontrados."),
            @ApiResponse(code = 404, message = "Clientes no encontrados."),
    })
    @GetMapping(value = "/edad/{edadString}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Cliente>> filtroEdad(@PathVariable String edadString) {
        if (NumberUtils.isDigits(edadString)) {
            int edad = Integer.parseInt(edadString);
            List<Cliente> clientes = clienteServicio.findByEdadGreaterThanEqual(edad);
            if (clientes.isEmpty()) {
                return new ResponseEntity<>(clientes,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clientes, HttpStatus.FOUND);
        }
        return new ResponseEntity(ResponseMessage.builder().code("400").response("Edad no válida.").build(), HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("Filtrar cliente por tipo y número de identidad")
    @ApiResponses({
            @ApiResponse(code = 302, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente no encontrado."),
    })
    @GetMapping("/id/{tipoId}/{numeroId}")
    public ResponseEntity<Cliente> filtroId(@PathVariable String tipoId, @PathVariable String numeroId) {
        Cliente cliente = clienteServicio.findByTipoIdAndNumeroId(tipoId, numeroId);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.FOUND);
    }

}
