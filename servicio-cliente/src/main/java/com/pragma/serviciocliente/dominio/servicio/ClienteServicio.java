package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.cliente.FotoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRespositorioInterfaz clienteRespositorio;

    @Autowired
    ClienteServicioUtils clienteServicioUtils;

    // Cliente Feign para comunicación con servicio Foto
    @Autowired
    FotoRest fotoRest;

    public void guadarCliente(Cliente cliente){
        clienteRespositorio.guardarCliente(cliente);
        fotoRest.guardarFoto(Foto.builder()
                .foto(cliente.getFoto())
                .IdCliente(cliente.getNumeroId()).build());
    }

    public List<Cliente> listarClientes(){
         List<Cliente> clientes = clienteRespositorio.listarClientes();
         return clientes.stream().map(cliente -> {
             Foto foto = clienteServicioUtils.getFoto(cliente);
             if(foto != null){
                 cliente.setFoto(foto.getFoto());
             }
             return  cliente;
            }).collect(Collectors.toList());
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


    public Cliente findByTipoIdAndNumeroId(String tipoId, String numeroId) {
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return optionalCliente.orElse(Cliente.builder().build());
    }


    public Boolean isUniqueId(String tipoId, String numeroId){
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return optionalCliente.isPresent();
    }


}
