package com.etienne.client.store.controller;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.ClientVisit;
import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/filter")
    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        return clientService.filterClients(filter, sortingParams, pagingParams);
    }

    @GetMapping(path = "/id")
    public Client findClientById(String id) throws ClientNotFoundException {
        return clientService.findClientById(id);
    }

    @PostMapping(path = "/new")
    @ResponseStatus(CREATED)
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
    }

    @GetMapping(path = "/visit/{visitId}")
    public ClientVisit findClientWithVisit(@PathVariable ObjectId visitId) {
        return clientService.findClientWithVisit(visitId);
    }

}
