package com.Fortinet.Fortinet.Servicio;

import com.Fortinet.Fortinet.DTO.usuarioDTO;
import com.Fortinet.Fortinet.Repositorio.usuarioRepositorio;
import com.Fortinet.Fortinet.Entidades.Usuario;
import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@NoArgsConstructor
public class servicioUsuario {

    @Autowired
    private usuarioRepositorio usuariorepositorio;


    @Autowired
    private emailService emailservice;
    public servicioUsuario(usuarioRepositorio usuariorepositorio, emailService emailservice){
        this.emailservice = emailservice;
        this.usuariorepositorio = usuariorepositorio;
    }

    public servicioUsuario(usuarioRepositorio usuariorepositorio){
        this.usuariorepositorio = usuariorepositorio;
    }

    public Usuario registroUsuario(usuarioDTO dto){

        // 1. UUID.randomUUID(): Genera un identificador único universal (UUID versión 4)
        // 2. .substring(0, 8): Toma los primeros 8 caracteres del UUID

        String codigo = "DSMP-" + UUID.randomUUID().toString().substring(0,3).toUpperCase();
        LocalDateTime expiracion = LocalDateTime.now().plusDays(7); // Genera fecha

        Usuario usuarioCreado  = Usuario.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .numeroTelefono(dto.getNumeroTelefono())
                .cargoUsuario(dto.getCargoUsuario())
                .correoUsuario(dto.getCorreoUsuario())
                .nombreEmpresa(dto.getNombreEmpresa())

                .build();


        Usuario creado = usuariorepositorio.save(usuarioCreado);

        try {
            emailservice.sendHtmlEmailTemplate(creado);
        }catch (MessagingException ex){
            ex.printStackTrace();
        }

        return creado; //  Retornar la entidad guardada

    }
    public List<Usuario> MostrarTodos(){
        return usuariorepositorio.findAll();
    }
    public List<Usuario> MostrarPorCargos(String cargoUsuario){
        return usuariorepositorio.findByCargoUsuario(cargoUsuario);
    }
    public List<Usuario>MostrarPorCorreo(String correoUsuario){
        return usuariorepositorio.findByCorreoUsuario(correoUsuario);
    }
}
