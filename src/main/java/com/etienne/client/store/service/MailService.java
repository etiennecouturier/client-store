package com.etienne.client.store.service;

import  com.etienne.client.store.model.domain.ClientVisit;
import com.etienne.client.store.model.domain.MailContent;
import com.etienne.client.store.model.exception.EMailAddressNotFoundException;
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

    private final ClientService clientService;

    public void sendMessage(MailContent mailContent) throws MessagingException, IOException {
        ClientVisit client = clientService.findClientWithVisit(mailContent.getVisitId());
        if (client.getEmail().isEmpty()) throw new EMailAddressNotFoundException();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(client.getEmail());
        helper.setSubject(mailContent.getTitle());
        helper.setText(mailContent.getBody());
        helper.addAttachment("vasarlas.pdf",
                new ByteArrayDataSource(pdfService.downloadPdf(client), APPLICATION_PDF_VALUE));
        emailSender.send(message);
    }

}
