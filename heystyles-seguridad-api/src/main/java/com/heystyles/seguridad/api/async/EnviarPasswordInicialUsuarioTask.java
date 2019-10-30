package com.heystyles.seguridad.api.async;

import com.heystyles.mail.client.MailClient;
import com.heystyles.mail.core.Mail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EnviarPasswordInicialUsuarioTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(EnviarPasswordInicialUsuarioTask.class);

    @Autowired
    private MailClient mailClient;

    @Autowired(required = false)
    private TemplateEngine templateEngine;

    private EnviarPasswordInicialUsuarioData data;

    public void setData(EnviarPasswordInicialUsuarioData enviarPasswordInicialUsuarioData) {
        this.data = enviarPasswordInicialUsuarioData;
    }

    @Override
    public void run() {
        LOGGER.info("Enviando email");
        if (data == null) {
            LOGGER.info("Fallo de configuracion, abortando.");
            return;
        }

        Context context = new Context();
        context.setVariable("email", data.getEmail());
        context.setVariable("password", data.getPassword());
        String body = templateEngine.process("enviarPasswordInicialUsuarioTemplate", context);

        LOGGER.debug("Email body: " + body);
        Mail mail = new Mail();
        mail.setSubject("BIENVENIDO A HEYSTYLES");
        mail.setText(body);
        mail.addToAddress(data.getEmail());
        try {
            mailClient.sendMailImmediate(mail);
            LOGGER.info("Envio de mail a " + data.getEmail());
        }
        catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }
}
