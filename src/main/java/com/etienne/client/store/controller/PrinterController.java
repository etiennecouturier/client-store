package com.etienne.client.store.controller;

import com.etienne.client.store.service.PrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.PrinterException;

@RestController
@RequestMapping(path = "/print")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping(path = "/visit")
    public void print() throws PrinterException {
        printerService.print(null);
    }

}
