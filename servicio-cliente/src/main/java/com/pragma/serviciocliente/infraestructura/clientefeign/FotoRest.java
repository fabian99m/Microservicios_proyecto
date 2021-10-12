package com.pragma.serviciocliente.infraestructura.clientefeign;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-foto", fallback = FotoHystrixFallback.class)
//@RequestMapping("/foto")
public interface FotoRest {

    @PostMapping("/foto")
    ResponseEntity<Foto> guardarFoto(@RequestBody Foto foto);

    @GetMapping("/foto/{IdCliente}")
    ResponseEntity<Foto> obternerFotoPorIdCliente(@PathVariable Long IdCliente);

    @DeleteMapping("/foto/{IdCliente}")
    ResponseEntity<Foto> EliminarFotoByIdCliente(@PathVariable Long IdCliente);

    @PutMapping("/foto/")
    ResponseEntity<Foto> actualizarFoto(@RequestBody Foto foto);
}
