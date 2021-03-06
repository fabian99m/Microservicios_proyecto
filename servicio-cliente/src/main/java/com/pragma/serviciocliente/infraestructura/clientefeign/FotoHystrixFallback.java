package com.pragma.serviciocliente.infraestructura.clientefeign;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class FotoHystrixFallback implements FotoRest {

    @Override
    public ResponseEntity<Foto> guardarFoto(Foto foto) {
         return new ResponseEntity<>(Foto.builder()
                .foto("No hay foto.")
                .IdCliente(-1L)
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Foto> obternerFotoPorIdCliente(Long IdCliente) {
        return new ResponseEntity<>(Foto.builder()
                .foto("Foto no encotroda.")
                .IdCliente(IdCliente)
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Foto> EliminarFotoByIdCliente(Long IdCliente) {
        return new ResponseEntity<>(Foto.builder()
                .foto("No hay foto.")
                .IdCliente(IdCliente)
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Foto> actualizarFoto(Foto foto) {
        return null;
    }
}
