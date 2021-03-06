package com.pragma.serviciocliente.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {

    private String nombres;
    private String apellidos;
    private String tipoId;
    private String numeroId;
    private int edad;
    private String ciudadNacimiento;

    @Transient
    private String foto;

    /*public enum TipoId {
        CEDULA,
        TARJETA_DE_IDENTIDAD
    } */
}






