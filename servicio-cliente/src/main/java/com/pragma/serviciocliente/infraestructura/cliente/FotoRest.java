package com.pragma.serviciocliente.infraestructura.cliente;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-foto", fallback = FotoHystrixFallback.class)
//@RequestMapping("/foto")
public interface FotoRest {

    @PostMapping("/foto")
    public ResponseEntity<Foto> guardarFoto(@RequestBody Foto foto);

    @GetMapping("/foto/{IdCliente}")
    public ResponseEntity<Foto> obternerFotoPorIdCliente(@PathVariable Long IdCliente);

    @DeleteMapping("/foto/{IdClient}")
    public ResponseEntity<Foto> EliminarFotoByIdCliente(@PathVariable Long IdCliente);
}
