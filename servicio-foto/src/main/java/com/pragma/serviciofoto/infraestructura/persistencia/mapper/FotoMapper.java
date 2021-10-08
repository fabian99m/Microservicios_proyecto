package com.pragma.serviciofoto.infraestructura.persistencia.mapper;

import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FotoMapper {

    Foto toFoto(FotoEntidad fotoEntidad);

    @InheritInverseConfiguration
    FotoEntidad toFotoEntidad(Foto foto);
}
