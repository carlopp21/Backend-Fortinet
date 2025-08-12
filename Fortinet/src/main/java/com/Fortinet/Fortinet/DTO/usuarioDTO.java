package com.Fortinet.Fortinet.DTO;
import com.Fortinet.Fortinet.Enum.enumLicencia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //
public class usuarioDTO {

    @NotBlank(message = "Nombre empresa no puede ser vacío")
    private String nombreEmpresa;

    @NotBlank(message = "Nombre de usuario no puede estar vacío")
    private String nombreUsuario;

    @NotBlank(message = "NIT de la empresa no puede estar vacío")
    private String nitEmpresa;

    @NotNull(message = "El tipo de licencia no puede ser nulo")
    private enumLicencia tipoLicencia;

    @NotBlank(message = "El puesto del usuario no puede estar vacío")
    private String cargoUsuario;

    @NotBlank(message = "Número de teléfono no puede estar vacío")
    @Pattern(regexp = "\\d{7,15}", message = "Número de teléfono inválido")
    private String numeroTelefono;

    @NotBlank(message = "El correo de usuario no puede estar vacío")
    @Email(message = "Formato de correo inválido")
    private String correoUsuario;




}
