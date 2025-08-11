package com.Fortinet.Fortinet.Servicio;


import com.Fortinet.Fortinet.Entidades.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class emailService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendHtmlEmailTemplate(Usuario usuario) throws MessagingException {
        Context ctx = new Context();
        ctx.setVariable("nombreEmpresa", usuario.getNombreEmpresa());
        ctx.setVariable("numeroTelefono", usuario.getNumeroTelefono());

        //Procesamiento-plantilla
        String htmlBody = templateEngine.process("email", ctx);

        // Aquí se llama correctamente al método de envío
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


