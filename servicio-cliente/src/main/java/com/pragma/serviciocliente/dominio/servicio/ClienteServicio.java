package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.cliente.FotoRest;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void guadarCliente(Cliente cliente) {
       ClienteEntidad clienteBd = clienteRespositorio.guardarCliente(cliente);
        fotoRest.guardarFoto(Foto.builder()
                .foto(cliente.getFoto())
                .IdCliente(clienteBd.getId()).build());
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientesBd = clienteRespositorio.listarClientes();
        return clientesBd.stream().map(clienteBd -> {
            Foto foto = clienteServicioUtils.getFoto(clienteBd);
            if (foto != null) {
                clienteBd.setFoto(foto.getFoto());
            }
            return clienteBd;
        }).collect(Collectors.toList());
    }

    public void eliminarCliente(Cliente cliente) {
        Long idClient = clienteServicioUtils.getIdClienteEntidad(cliente.getTipoId(), cliente.getNumeroId());
        System.out.println("id cliente: "+idClient);
        clienteRespositorio.eliminarCliente(idClient);
        fotoRest.EliminarFotoByIdCliente(idClient);
    }

    public void actualizarCliente(Cliente cliente){
        Long idClientEntidad = clienteServicioUtils.getIdClienteEntidad(cliente.getTipoId(), cliente.getNumeroId());
        clienteRespositorio.actulizarCliente(cliente,idClientEntidad);
        if(cliente.getFoto() != null) {
           // fotoRest.guardarFoto(Foto.builder().foto(cliente.getFoto()).IdCliente(idClient).build());
        }
     }

    public List<Cliente> findByEdadGreaterThanEqual(int edad) {
        Optional<List<Cliente>> optionalList = clienteRespositorio.findByEdadGreaterThanEqual(edad);
        List<Cliente> clientesBd = optionalList.orElse(Collections.emptyList());
        if (clientesBd.isEmpty()) {
            return Collections.emptyList();
        }
        return clientesBd.stream().map(clienteBd -> {
            Foto foto = clienteServicioUtils.getFoto(clienteBd);
            if (foto != null) {
                clienteBd.setFoto(foto.getFoto());
            }
            return clienteBd;
        }).collect(Collectors.toList());
    }

    public Cliente findByTipoIdAndNumeroId(String tipoId, String numeroId) {
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId, numeroId);
        Cliente clienteBd = optionalCliente.orElse(null);
        if (clienteBd == null) {
            return null;
        }
        clienteBd.setFoto(clienteServicioUtils.getFoto(clienteBd).getFoto());
        return clienteBd;
    }
}
