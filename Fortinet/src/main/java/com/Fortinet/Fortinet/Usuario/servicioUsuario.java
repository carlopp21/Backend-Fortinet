package com.Fortinet.Fortinet.Usuario;

import com.example.registro.DTO.usuarioDTO;
import com.example.registro.Entidades.Usuario;
import com.example.registro.Repositorio.usuarioRepositorio;
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
                .sectorIndustrial(dto.getSectorIndustrial())
                .cargoUsuario(dto.getCargoUsuario())
                .correoUsuario(dto.getCorreoUsuario())
                .lugarUsuario(dto.getLugarUsuario())
                .nombreEmpresa(dto.getNombreEmpresa())
                .codigoDescuento(codigo)
                .expiracionCodigo(expiracion)
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
    public List<Usuario> MostrarPorSector(String sectorIndustrial){
        return usuariorepositorio.findBySectorIndustrial(sectorIndustrial);
    }
    public List<Usuario> MostrarPorCargos(String cargoUsuario){
        return usuariorepositorio.findByCargoUsuario(cargoUsuario);
    }
    public List<Usuario>MostrarPorLugar(String lugarUsuario){
        return usuariorepositorio.findByLugarUsuario(lugarUsuario);
    }
    public List<Usuario>MostrarPorCorreo(String correoUsuario){
        return usuariorepositorio.findByCorreoUsuario(correoUsuario);
    }
}
