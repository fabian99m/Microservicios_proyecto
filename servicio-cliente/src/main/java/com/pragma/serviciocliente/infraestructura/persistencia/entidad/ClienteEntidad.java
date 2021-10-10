package com.pragma.serviciocliente.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cliente")
@NoArgsConstructor @AllArgsConstructor
@DynamicUpdate
public class ClienteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombres;

    @NonNull
    private String apellidos;

    @NonNull
    private int edad;

    @NonNull
    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    @NonNull
    @Column(name = "tipo_identificacion")
    private String tipoId;

    @NonNull
    @Column(name = "numero_identificacion")
    private String numeroId;
}
