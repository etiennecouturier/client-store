package com.etienne.client.store.controller;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static java.util.Collections.sort;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping(path = "/id")
    public Client getClientById(String id) throws Exception {
        return clientRepository.findById(id)
                .map(client -> {
                    sort(client.getVisits());
                    return client;
                })
                .orElseThrow(() -> new Exception("Client not found"));
    }

    @GetMapping(path = "/ping")
    public void ping() {}

    @PostMapping(path = "/new")
    @ResponseStatus(CREATED)
    public Client addClient(@RequestBody Client client) {
        Client resultClient = clientRepository.save(client);
        sort(resultClient.getVisits());
        return resultClient;
    }

    @GetMapping()
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/visit-count-last-10-days")
    public List<CountPerDate> getVisitCountForLast10Days() {
        return clientRepository.findVisitCountForLast10Days();
    }

    @GetMapping(path = "/filter")
    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        return clientRepository.findAll(
                clientExample(filter),
                of(pagingParams.getPage(), pagingParams.getSize(), sortingParams.getSorting())
        );

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") String id) {
        clientRepository.deleteById(id);
    }

}
