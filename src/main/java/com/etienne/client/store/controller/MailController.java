package com.etienne.client.store.controller;

import com.etienne.client.store.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(path = "/mail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {

    private final MailService mailService;

    @GetMapping(path = "/send")
    public void downloadPdf() throws IOException, MessagingException {
        mailService.sendMessage("iistvan.szabo2@gmail.com",
                "rendelés részletei",
                "Kedves vásárló!\n\n" +
                        "A mai látogatásának részleteit a csatolt fájl tartalmazza.\n\n" +
                        "Üdvözlettel,\n" +
                        "Gyöngyi Optika");
    }

}
