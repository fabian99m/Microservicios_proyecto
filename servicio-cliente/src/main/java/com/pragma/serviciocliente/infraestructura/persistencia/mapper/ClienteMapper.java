package com.pragma.serviciocliente.infraestructura.persistencia.mapper;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

        @Mappings({
                @Mapping(target = "foto",ignore = true)
        })
        Cliente toCliente(ClienteEntidad clienteEntidad);

        List<Cliente> toListCliente(List<ClienteEntidad> listClienteEntidad);

        @InheritInverseConfiguration
        @Mapping(target = "id",ignore = true)
        ClienteEntidad toClienteEntidad(Cliente cliente);
}
