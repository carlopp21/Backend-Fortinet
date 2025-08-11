package com.Fortinet.Fortinet.Entidades;

import com.Fortinet.Fortinet.Enum.enumLicencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="Usuarios")
@Data @NoArgsConstructor @AllArgsConstructor @Builder @Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    private Long idUsuario;

    @NotBlank(message = "Nombre empresa no puede ser vacio")
    private String nombreEmpresa;

    @NotBlank(message = "Nombre de usuario no puede estar vacio")
    private String nombreUsuario;

    @NotBlank(message = "Nombre de usuario no puede estar vacio")
    private String nitEmpresa;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Nombre de usuario no puede estar vacio")
    private enumLicencia tipoLicencia;

    @NotBlank(message = "El puesto del usuario no puede estar vacio")
    private String cargoUsuario;

    @NotBlank(message = "Numero de telefono no puede estar vacio")
    private String numeroTelefono;

    @NotBlank(message = "El correo de usuario no puede estar vacio")
    private String correoUsuario;
;

}
