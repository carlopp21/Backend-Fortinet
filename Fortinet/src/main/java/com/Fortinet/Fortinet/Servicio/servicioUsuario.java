package com.Fortinet.Fortinet.Servicio;

import com.Fortinet.Fortinet.DTO.usuarioDTO;
import com.Fortinet.Fortinet.Repositorio.usuarioRepositorio;
import com.Fortinet.Fortinet.Entidades.Usuario;
import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Usuario registroUsuario(usuarioDTO dto){
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
