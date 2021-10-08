package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRespositorioInterfaz clienteRespositorio;

    public void guadarCliente(Cliente cliente){
            clienteRespositorio.guardarCliente(cliente);
    }

    public List<Cliente> listarClientes(){

       return clienteRespositorio.listarClientes();
    }

    public void eliminarCliente(Cliente cliente){
        clienteRespositorio.eliminarCliente(cliente);
    }

    //public void actualizarCliente(Cliente cliente){
   ////    // clienteRespositorio
   // }

    public List<Cliente> findByEdadGreaterThanEqual(int edad) {
        Optional<List<Cliente>> optionalList = clienteRespositorio.findByEdadGreaterThanEqual(edad);
        return optionalList.orElse(Collections.emptyList());
    }

    public Boolean isUniqueId(String tipoId, String numeroId){
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return optionalCliente.isPresent();
    }

    public Cliente findByTipoIdAndNumeroId(String tipoId, String numeroId) {
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return optionalCliente.orElse(Cliente.builder().build());
    }


}
