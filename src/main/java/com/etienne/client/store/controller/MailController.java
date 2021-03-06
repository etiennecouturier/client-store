package com.etienne.client.store.controller;

import com.etienne.client.store.model.domain.MailContent;
import com.etienne.client.store.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(path = "/mail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {

    private final MailService mailService;

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void sendMail(@RequestBody MailContent mailContent) throws IOException, MessagingException {
        mailService.sendMessage(mailContent);
    }

}
