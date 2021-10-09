package com.pragma.serviciocliente.dominio.repositorio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;

import java.util.List;
import java.util.Optional;

public interface ClienteRespositorioInterfaz {

    ClienteEntidad guardarCliente(Cliente cliente);

    void eliminarCliente(Cliente cliente);

    void ActulizarCliente(Cliente cliente);

    List<Cliente> listarClientes();

    Optional<List<Cliente>> findByEdadGreaterThanEqual(Integer edad);

    Optional<Cliente> findByTipoIdAndNumeroId(String tipoId, String numeroId);

    public Optional<Long> getIdCliente(String tipoId, String numeroId);
}
