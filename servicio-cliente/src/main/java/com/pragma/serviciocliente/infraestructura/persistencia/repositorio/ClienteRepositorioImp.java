package com.pragma.serviciocliente.infraestructura.persistencia.repositorio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.DAO.ClienteDaoInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositorioImp implements ClienteRespositorioInterfaz {

    @Autowired
    ClienteDaoInterfaz clienteDao;

    @Autowired
    ClienteMapper clienteMapper;

    @Override
    public ClienteEntidad guardarCliente(Cliente cliente) {
        ClienteEntidad clienteEntidad = clienteMapper.toClienteEntidad(cliente);
        return clienteDao.save(clienteEntidad);
    }

    @Override
    public void eliminarCliente(Long IdCliente) {
        clienteDao.deleteById(IdCliente);
    }

    @Override
    public void actulizarCliente(Cliente cliente,Long IdClienteEntidad) {
        ClienteEntidad clienteEntidad = clienteMapper.toClienteEntidad(cliente);
        clienteEntidad.setId(IdClienteEntidad);
        clienteDao.save(clienteEntidad);
       // ClienteEntidad clienteEntidad = clienteMapper.toClienteEntidad(cliente);
        //clienteDao.   (clienteEntidad);
    }

    @Override
    public List<Cliente> listarClientes() {
        List<ClienteEntidad> listaClienteEntidad = (List<ClienteEntidad>) clienteDao.findAll();
        return clienteMapper.toListCliente(listaClienteEntidad);
    }

    @Override
    public Optional<List<Cliente>> findByEdadGreaterThanEqual(Integer edad) {
        Optional<List<ClienteEntidad>> clienteEntidads = clienteDao.findByEdadGreaterThanEqual(edad);
        return clienteEntidads.map( entidad -> clienteMapper.toListCliente(entidad));
    }

    @Override
    public Optional<Cliente> findByTipoIdAndNumeroId(String tipoId, String numeroId) {
        Optional<ClienteEntidad> clienteEntidad = clienteDao.findByTipoIdAndNumeroId(tipoId,numeroId);
        return clienteEntidad.map( entidad -> clienteMapper.toCliente(entidad));

    }

    public Optional<Long> getIdCliente(String tipoId, String numeroId){
       return clienteDao.findByTipoIdAndNumeroId(tipoId, numeroId).map(ClienteEntidad::getId);
    }

}
