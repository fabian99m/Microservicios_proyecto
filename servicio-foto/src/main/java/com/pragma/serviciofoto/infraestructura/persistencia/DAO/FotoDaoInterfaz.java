package com.pragma.serviciofoto.infraestructura.persistencia.DAO;

import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoDaoInterfaz extends MongoRepository<FotoEntidad,Long> {

    public Optional<FotoEntidad> findByIdCliente(Long id);

    public void deleteFotoByIdCliente(Long id);
}
