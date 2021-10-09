package com.pragma.serviciocliente.infraestructura.cliente;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-foto")
@RequestMapping("/foto")
public interface FotoRest {

    @PostMapping
    public ResponseEntity<Foto> guardarFoto(@RequestBody Foto foto);

    @GetMapping("/{IdCliente}")
    public ResponseEntity<Foto> obternerFotoPorIdCliente(@PathVariable String IdCliente);
}
