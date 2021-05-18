package com.okta.springbootmongo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private final ClientRepository clientRepository;

    @PostMapping()
    public @ResponseBody
    Mono<Client> addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping()
    public @ResponseBody
    Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
