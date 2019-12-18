package com.feelagro.Proyecto.Final.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {

@Autowired
private JavaMailSender mailsender;
 
 @Autowired
private MedicionServicio medicionServicio;

@Async
public void enviar(String cuerpo, String titulo, String mail) {

SimpleMailMessage mensaje = new SimpleMailMessage();
mensaje.setTo(mail);
mensaje.setFrom("noreply@feelagro.com");
mensaje.setSubject(titulo);
mensaje.setText(cuerpo);

mailsender.send(mensaje);
        
        

    }
}
