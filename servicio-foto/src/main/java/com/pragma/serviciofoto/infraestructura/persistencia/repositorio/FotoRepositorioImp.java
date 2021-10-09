package com.pragma.serviciofoto.infraestructura.persistencia.repositorio;


import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.dominio.repositorio.FotoRespositorioInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.DAO.FotoDaoInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.mapper.FotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FotoRepositorioImp implements FotoRespositorioInterfaz {

    @Autowired
    private FotoDaoInterfaz fotoDao;

    @Autowired
    private FotoMapper fotoMapper;

    @Override
    public void guardarFoto(Foto foto) {
        fotoDao.save(fotoMapper.toFotoEntidad(foto));
    }

    @Override
    public void deleteFotoByIdCliente(Long id) {
        fotoDao.deleteFotoByIdCliente(id);
    }

    @Override
    public Optional<Foto> findByIdCliente(Long id) {
        return fotoDao.findByIdCliente(id).map(fotoEntidad-> fotoMapper.toFoto(fotoEntidad));
    }
}
