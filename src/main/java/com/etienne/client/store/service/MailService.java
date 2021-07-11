package com.etienne.client.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

/**
 * gmail setup
 * https://stackoverflow.com/questions/35347269/javax-mail-authenticationfailedexception-535-5-7-8-username-and-password-not-ac
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailService {

    private final JavaMailSender emailSender;

    private final PdfService pdfService;

    public void sendMessage(String to, String subject, String text) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.addAttachment("rendeles.pdf",
                new ByteArrayDataSource(pdfService.downloadPdf("60eb467ef92415259810f02b").getInputStream(),
                APPLICATION_PDF_VALUE));
        emailSender.send(message);
    }

}
