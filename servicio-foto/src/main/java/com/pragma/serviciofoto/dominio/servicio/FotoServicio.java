package com.pragma.serviciofoto.dominio.servicio;

import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.infraestructura.persistencia.repositorio.FotoRepositorioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorioImp fotoRepositorio;

    public void guardarFoto(Foto foto){
        fotoRepositorio.guardarFoto(foto);
    }

    public void EliminarFotoByIdCliente(Long idClient){
        fotoRepositorio.deleteFotoByIdCliente(idClient);
    }

    public Foto obternerFotoPorIdCliente(Long idClient){
        return fotoRepositorio.findByIdCliente(idClient).orElse(null);
    }

    public void actualizarFoto(Foto foto){
        fotoRepositorio.actualizarFoto(foto);
    }

}
