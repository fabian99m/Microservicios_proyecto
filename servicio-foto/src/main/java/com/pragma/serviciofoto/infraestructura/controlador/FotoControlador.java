package com.pragma.serviciofoto.infraestructura.controlador;


import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.dominio.servicio.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    @PostMapping
    public ResponseEntity<Foto> guardarFoto(@RequestBody Foto foto) {
        fotoServicio.guardarFoto(foto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{IdCliente}")
    public ResponseEntity<Foto> obternerFotoPorIdCliente(@PathVariable Long IdCliente) {
        Foto foto = fotoServicio.obternerFotoPorIdCliente(IdCliente);
        if(foto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foto,HttpStatus.OK);
    }

    @DeleteMapping("/{IdClient}")
    public ResponseEntity<Foto>  EliminarFotoByIdCliente(@PathVariable Long IdClient){
        fotoServicio.EliminarFotoByIdCliente(IdClient);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
