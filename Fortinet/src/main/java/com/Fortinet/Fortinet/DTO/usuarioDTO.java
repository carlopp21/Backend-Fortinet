package com.Fortinet.Fortinet.DTO;
import com.Fortinet.Fortinet.Enum.enumLicencia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //
public class usuarioDTO {

    @NotBlank(message = "Nombre empresa no puede ser vacio")
    private String nombreEmpresa;

    @NotBlank(message = "Nombre de usuario no puede estar vacio")
    private String nombreUsuario;

    @NotBlank(message = "Nombre de usuario no puede estar vacio")
    private String nitEmpresa;

    private enumLicencia tipoLicencia;

    @NotBlank(message = "El puesto del usuario no puede estar vacio")
    private String cargoUsuario;

    @NotBlank(message = "Numero de telefono no puede estar vacio")
    private String numeroTelefono;

    @NotBlank(message = "El correo de usuario no puede estar vacio")
    private String correoUsuario;




}
