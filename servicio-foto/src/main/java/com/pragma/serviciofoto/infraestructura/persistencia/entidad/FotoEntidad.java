package com.pragma.serviciofoto.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fotos")
@Data @AllArgsConstructor @NoArgsConstructor
public class FotoEntidad {

    private String foto;
    private String idCliente;
}
