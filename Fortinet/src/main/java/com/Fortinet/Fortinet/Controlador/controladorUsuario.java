package com.Fortinet.Fortinet.Controlador;

import com.Fortinet.Fortinet.DTO.usuarioDTO;
import com.Fortinet.Fortinet.Entidades.Usuario;
import com.Fortinet.Fortinet.Servicio.servicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(
        origins = {
                "https://fortinet-5ifb.vercel.app",
                "https://fortinet-5ifb-jdlkh2l4i-carlos-joel-mancipe-arrietas-projects.vercel.app"
        },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS },
        allowedHeaders = "*"
)


@RestController
@RequestMapping("api/usuario")
public class controladorUsuario {

    @Autowired
    servicioUsuario serviciousuario;

    public controladorUsuario(servicioUsuario serviciousuario){
        this.serviciousuario = serviciousuario;
    }

    @PostMapping("/public/registro")
    //Vamos a enviar los campos que estan en usuarioDTO
    public ResponseEntity<?> registro(@RequestBody usuarioDTO request, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()){
                //Mapeo con FieldError a : "campo mensaje"
                List<String> errores = bindingResult.getFieldErrors()
                        .stream()
                        .map(fe-> fe.getField()+ ": "+ fe.getDefaultMessage())
                        .collect((Collectors.toList()));

                Map<String, Object> response = new HashMap<>();
                response.put("status", HttpStatus.BAD_REQUEST.value());
                response.put("Error", errores);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response);
            }

            // Llamada al servicio
            Usuario creado = serviciousuario.registroUsuario(request);
            // 1. Map<String, Object>: 	"Quiero una caja para guardar regalos con etiquetas de papel"
            //    - String: Tipo de la clave (ej: "id", "mensaje")
            //    - Object: Tipo del valor (puede ser cualquier tipo de dato)
            // 2. new HashMap<>(): 	Compras una caja de cartón real PARA GUARDAR COSAS
            Map<String, Object> response = new HashMap<>();
            response.put("id",creado.getIdUsuario());
            response.put("Mensaje:", "Registro Uusario");
            response.put("nombreEmpresa", creado.getNombreEmpresa());
            response.put("nombreUsuario", creado.getNombreUsuario());
            response.put("cargoUsuario ", creado.getCargoUsuario());
            response.put("numeroTelefono ", creado.getNumeroTelefono());
            response.put("correoUsuario ", creado.getCorreoUsuario());
            return ResponseEntity.ok(response);

        }catch(IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar: " + e.getMessage());
        }
    }
    @GetMapping("private")
    public ResponseEntity<?> MostrarTodos(){
        List<Usuario> todos = serviciousuario.MostrarTodos();
        return ResponseEntity.ok(todos);
    }

    // Corrige los endpoints duplicados en controladorUsuario.java
    @GetMapping("private/cargo/{cargoUsuario}")  // Cambiado
    public ResponseEntity<?> MostrarCargo(@PathVariable String cargoUsuario) {
        try {
            List<Usuario> Cargos = serviciousuario.MostrarPorCargos(cargoUsuario);
            return ResponseEntity.ok(Cargos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al mostrar " + e.getMessage());
        }
    }

    @GetMapping("private/correo/{correoUsuario}")  // Cambiado
    public ResponseEntity<?> mostrarCorreo(@PathVariable String correoUsuario) {
        try {
        List<Usuario> Correo = serviciousuario.MostrarPorCorreo(correoUsuario);
        return ResponseEntity.ok(Correo);
        }catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al mostrar " + e.getMessage());
        }
    }

    @RequestMapping(
            value = "/public/registro",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "https://fortinet-5ifb.vercel.app")
                .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }


}
