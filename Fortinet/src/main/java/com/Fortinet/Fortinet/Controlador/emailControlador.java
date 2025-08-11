package com.Fortinet.Fortinet.Controlador;

import com.Fortinet.Fortinet.Servicio.servicioUsuario;
import com.Fortinet.Fortinet.Entidades.Usuario;
import com.Fortinet.Fortinet.DTO.usuarioDTO;
import com.Fortinet.Fortinet.Servicio.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "https:localhost:3000")
public class emailControlador {

    @Autowired
    emailService emailservice;

    @Autowired
    private servicioUsuario serviciousuario;

    @PostMapping("/send")
    public ResponseEntity<?> sendResgistrationEmail(@RequestBody usuarioDTO request){
        try{
            Usuario creado = serviciousuario.registroUsuario(request);

            emailservice.sendHtmlEmailTemplate(creado);
            return ResponseEntity.ok(Map.of("status", "email enviado"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
