package com.etienne.client.store.controller;

import com.etienne.client.store.model.Client;
import com.etienne.client.store.repository.ClientRepository;
import com.etienne.client.store.model.PagingParams;
import com.etienne.client.store.model.SortingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static java.util.Collections.sort;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping(path = "/id")
    public @ResponseBody
    Client getClientById(String id) throws Exception {
        return clientRepository.findById(id)
                .map(client -> {
                    sort(client.getVisits());
                    return client;
                })
                .orElseThrow(() -> new Exception("Client not found"));
    }

    @GetMapping(path = "/ping")
    public @ResponseBody
    String ping() {
        return "hello";
    }

    @PostMapping(path = "/new")
    @ResponseBody
    @ResponseStatus(CREATED)
    public Client addClient(@RequestBody Client client) {
        Client resultClient = clientRepository.save(client);
        sort(resultClient.getVisits());
        return resultClient;
    }

    @GetMapping()
    public @ResponseBody
    List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/filter")
    @ResponseBody
    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        return clientRepository.findAll(
                clientExample(filter),
                of(pagingParams.getPage(), pagingParams.getSize(), sortingParams.getSorting())
        );

    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") String id) {
        clientRepository.deleteById(id);
    }

}
