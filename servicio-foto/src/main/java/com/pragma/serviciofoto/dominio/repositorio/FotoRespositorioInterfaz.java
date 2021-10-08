package com.pragma.serviciofoto.dominio.repositorio;

import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;

import java.util.Optional;

public interface FotoRespositorioInterfaz {

    void guardarFoto(Foto foto);

    void deleteFotoByIdCliente(String id);

     Optional<Foto> findByIdCliente(String id);

}
