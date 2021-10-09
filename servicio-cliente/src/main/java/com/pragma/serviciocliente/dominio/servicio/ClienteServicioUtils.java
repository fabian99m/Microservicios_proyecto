package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.cliente.FotoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServicioUtils {

    @Autowired
    private ClienteRespositorioInterfaz clienteRespositorio;

    @Autowired
    FotoRest fotoRest;
    public Boolean isUniqueId(String tipoId, String numeroId){
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId,numeroId);
        return optionalCliente.isPresent();
    }

    public Foto getFoto(Cliente cliente) {
        Foto foto = null;
        ResponseEntity<Foto> responseEntityFoto = fotoRest.obternerFotoPorIdCliente(cliente.getNumeroId());
        if(responseEntityFoto.getStatusCode() == HttpStatus.OK){
            foto = (Foto) responseEntityFoto.getBody();
        }
        return foto;
    }

}
