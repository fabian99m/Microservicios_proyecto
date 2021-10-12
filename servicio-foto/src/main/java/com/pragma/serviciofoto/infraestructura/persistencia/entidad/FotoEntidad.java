package com.pragma.serviciofoto.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fotos")
@Data @AllArgsConstructor @NoArgsConstructor
public class FotoEntidad {

    @Id
    private String id;
    private String foto;
    private Long idCliente;
}
