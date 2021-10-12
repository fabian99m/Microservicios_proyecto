package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRespositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.clientefeign.FotoRest;
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
    FotoRest fotoRest; // cliente Feign

    public Boolean existId(String tipoId, String numeroId) {
        Optional<Cliente> optionalCliente = clienteRespositorio.findByTipoIdAndNumeroId(tipoId, numeroId);
        return optionalCliente.isPresent();
    }

    public Foto getFoto(Cliente cliente) {
        Long idClient = this.getIdClienteEntidad(cliente.getTipoId(), cliente.getNumeroId());
        Foto foto =  null;
        ResponseEntity<Foto> responseEntityFoto = fotoRest.obternerFotoPorIdCliente(idClient);
        if (responseEntityFoto.getStatusCode() == HttpStatus.OK && idClient != -1L) {
            foto = (Foto) responseEntityFoto.getBody();
        }
        return foto;
    }

    public Long getIdClienteEntidad(String tipoId, String numeroId) {
        Optional<Long> idOptional = clienteRespositorio.getIdCliente(tipoId, numeroId);
        return idOptional.orElse(-1L);
    }

}
