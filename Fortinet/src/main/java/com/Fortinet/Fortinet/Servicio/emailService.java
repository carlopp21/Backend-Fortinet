package com.Fortinet.Fortinet.Servicio;


import com.Fortinet.Fortinet.Entidades.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class emailService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendHtmlEmailTemplate(Usuario usuario) throws MessagingException {
        Context ctx = new Context();
        // Variables que usa la plantilla (asegúrate que los getters existen en Usuario)
        ctx.setVariable("nombreEmpresa", usuario.getNombreEmpresa());
        ctx.setVariable("nombreUsuario", usuario.getNombreUsuario());
        ctx.setVariable("cargoUsuario", usuario.getCargoUsuario());
        ctx.setVariable("numeroTelefono", usuario.getNumeroTelefono());
        ctx.setVariable("correoUsuario", usuario.getCorreoUsuario());
        ctx.setVariable("nitEmpresa", usuario.getNitEmpresa());
        ctx.setVariable("tipoLicencia", usuario.getTipoLicencia() != null ? usuario.getTipoLicencia().name() : null);

        String htmlBody = templateEngine.process("email", ctx);

        // Puedes loguear htmlBody para depurar:
        System.out.println("HTML a enviar:\n" + htmlBody);

        sendhtmlEmail(
                "comunicaciones@dataservic.com",
                "Registro completo de " + usuario.getNombreUsuario(),
                htmlBody
        );
    }


    public void sendhtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("carlos.mancipe@dataservic.com");
        helper.setTo(to);
        helper.setSubject(subject);
        //Envio-HTML
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
    }
}


